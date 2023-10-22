import {Socket} from 'net';

import robot from "robotjs";
import { SerialPort } from 'serialport'
const port = new SerialPort({
    path: 'COM7',
    baudRate: 115200,
})

port.open(function (err) {
    if (err) {
        return console.log('Error opening port: ', err)
    }
    console.log("success")
});

export class Color {
    /**
     * Construct a new color
     * @param r 0-1
     * @param g 0-1
     * @param b 0-1
     */
    constructor(r, g, b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    toHex() {
        const r = Math.round(this.r * 255)
            .toString(16)
            .toUpperCase();
        const g = Math.round(this.g * 255)
            .toString(16)
            .toUpperCase();
        const b = Math.round(this.b * 255)
            .toString(16)
            .toUpperCase();
        // it should be a 6 digit hex number, left pad with 0s if necessary

        return `${r.padStart(2, '0')}${g.padStart(2, '0')}${b.padStart(2, '0')}`;
    }

    mult(factor) {
        return new Color(this.r * factor, this.g * factor, this.b * factor);
    }

    add(r, g, b) {
        return new Color(this.r + r, this.g + g, this.b + b);
    }

    addColor(color) {
        return this.add(color.r, color.g, color.b);
    }

    sub(r, g, b) {
        return new Color(this.r - r, this.g - g, this.b - b);
    }

    subColor(color) {
        return this.sub(color.r, color.g, color.b);
    }

    /**
     * Create a color from a hsv values
     * @param hue [0-1]
     * @param saturation [0-1]
     * @param brightness [0-1]
     */
    static fromHSV(hue, saturation, brightness) {
        const i = Math.floor(hue * 6);
        const f = hue * 6 - i;
        const p = brightness * (1 - saturation);
        const q = brightness * (1 - f * saturation);
        const t = brightness * (1 - (1 - f) * saturation);
        switch (i % 6) {
            case 0:
                return new Color(brightness, t, p);
            case 1:
                return new Color(q, brightness, p);
            case 2:
                return new Color(p, brightness, t);
            case 3:
                return new Color(p, q, brightness);
            case 4:
                return new Color(t, p, brightness);
            default:
                return new Color(brightness, p, q);
        }
        //throw new Error("math doesn't work");
    }
}


const maxLEDsPerRequest = 56;

export class LedStrip {
    state;


    #connection;

    constructor(address, length) {
        this.address = address;
        this.length = length;

        this.state = new Array(length).fill(new Color(0, 0, 0));
    }

    async init() {
        this.#connection = new Socket();

        return new Promise((resolve) => {
            this.#connection.connect({host: '192.168.1.100', port: 3333}, () => {
                console.log('Connected')
                resolve();
            });
        });
    }

    generatePayload() {
        const payload = new Uint8Array(this.state.length * 3);

        for (let i = 0; i < this.length; i++) {
            const color = this.state[i];
            payload[i * 3] = Math.floor(color.r * 255);
            payload[i * 3 + 1] = Math.floor(color.g * 255);
            payload[i * 3 + 2] = Math.floor(color.b * 255);
        }

        return payload;
    }

    update() {
        const payload = this.generatePayload();
        port.write(payload);
    }

    close() {
        this.#connection.close();
    }

}

let asd = 1;

let ledcount = 247;
const ledStrip = new LedStrip("", ledcount);
//await ledStrip.init();

function clamp(num, min, max) {
    return num <= min ? min : num >= max ? max : num;
}

const a = Color.fromHSV(0, 1, 1);
const b = Color.fromHSV(.6, 1, .1);

const screenWidth = 1536;

function getNormalizedMouseX() {
    return clamp(robot.getMousePos().x / screenWidth, 0, 1);
}

function normToLedPos(normPos) {
    return Math.floor(normPos * ledcount);
}

function redBluePeak(peakPos, brightnessDifference = .4) {
    const peakLedPos = normToLedPos(peakPos);
    for (let i = 0; i < ledcount; i++) {
        let distance = Math.abs(peakLedPos - i);
        let closeness = clamp(Math.pow(distance, .5) / 10, 0, 1);

        ledStrip.state[i] = Color.fromHSV(.6 + .4 * closeness, 1, 1 - brightnessDifference * closeness);
    }
}


let counter = 0;

let target = 0;
let current = 0;

const bed = {begin: 160, end: 247};
const desk = {begin: 0, end: 110};
const mirror = {begin: 110, end: 160};

function setRangeColor(range, color) {
    for (let i = range.begin; i < range.end; i++) {
        ledStrip.state[i] = color;
    }
}

class Animation {
    onUpdate() {
    }
}

class SolidColorAnimation extends Animation {
    constructor(range, color) {
        super();
        this.range = range;
        this.color = color;
    }

    onUpdate() {
        setRangeColor(this.range, this.color);
    }
}

const ledToNormPos = (ledPos) => ledPos / ledcount;

function lerpRangeToLedPos(range, t) {
    return clamp(Math.floor(range.begin + t * (range.end - range.begin)), 0, ledcount - 1);
}

class LinearOneAnimation extends Animation {
    constructor(range, bgColor, oneColor, velocity) {
        super();
        this.range = range;
        this.bgColor = bgColor;
        this.oneColor = oneColor;
        this.velocity = velocity;
        this.current = 0;
    }


    onUpdate() {
        setRangeColor(this.range, this.bgColor);
        ledStrip.state[lerpRangeToLedPos(this.range, this.current)] = this.oneColor;

        this.current += this.velocity;
        if (this.current < 0) {
            this.current = 1;
        } else if (this.current >= 1) {
            this.current = 0;
        }
    }
}

class RainbowWaveAnimation extends Animation {
    constructor(range, speed = 1) {
        super();
        this.range = range;
        this.speed = speed;
        this.offset = 0;
    }

    onUpdate() {
        let numLEDs = this.range.end - this.range.begin;
        for (let i = 0; i < numLEDs; i++) {
            let hue = (i + this.offset) % 360 / 360;
            let color = this.hsvToRgb(hue, 1, 1);
            ledStrip.state[this.range.begin + i] = color;
        }
        this.offset = (this.offset + this.speed) % 360;
    }

    // Helper method to convert HSV to RGB
    hsvToRgb(h, s, v) {
        let f = (n) => {
            const k = (n + h * 6) % 6;
            return v - v * s * Math.max(Math.min(k, 4 - k, 1), 0);
        }
        const r = Math.floor(f(5) * 255);
        const g = Math.floor(f(3) * 255);
        const b = Math.floor(f(1) * 255);
        return new Color(r / 255, g / 255, b / 255);
    }
}

let animations = [
    new LinearOneAnimation(bed, new Color(.6, .22, .9), new Color(1, 0, .5), -.002),
    new LinearOneAnimation(desk, new Color(.6, .22, .9), new Color(1, 0, .5), .002),
]
setInterval(() => {
    animations.forEach(animation => animation.onUpdate());

    // print the bytes of the uint8array seperated by a space. every byte should be in hexadecimal, 2 digits long
    // console.log(Array.from(ledStrip.generatePayload()).map((byte) => byte.toString(16).padStart(2, '0')).join(' '));
    ledStrip.update();
}, 10);
