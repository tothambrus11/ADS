# Algorithms and Data Structures

## Sequences
### Abstract interfaces:
- queue
- dequeue
- stack

### Implementations:
- ArrayQueue (queue)
- SinglyLinkedList (implements dequeue but some methods are O(n))
- DoublyLinkedList (dequeue)
- ArrayStack (stack)
- LinkedStack (stack)

## Trees
- todo

## Partitioning Algorithms
- Lomuto partition scheme
- Hoare partition scheme

## Sorting Algorithms
- Insertion Sort
- Selection Sort
- Bubble Sort
- Heap Sort
- Merge Sort
- Quick Sort
  - different partitioning schemes
- Bucket Sort

<table>
  <tr>
    <th>Sorting Algorithm</th>
    <th>Best Case Time</th>
    <th>Worst Case Time</th>
    <th>Average Case Time</th>
    <th>Space Complexity</th>
  </tr>
  <tr>
    <td>Selection Sort</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:lightgreen">O(1)</td>
  </tr>
  <tr>
    <td>Insertion Sort</td>
    <td style="color:green">O(n)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:lightgreen">O(1)</td>
  </tr>
  <tr>
    <td>Bubble Sort</td>
    <td style="color:green">O(n)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:lightgreen">O(1)</td>
  </tr>
<tr>
    <td>Bucket Sort</td>
    <td style="color:green">O(n+k)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:green">O(n+k)</td>
    <td style="color:green">O(n+k)</td>
  </tr>
  <tr>
    <td>In-place Quicksort</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:lightgreen">O(log n)</td>
  </tr>
  <tr>
    <td>Merge Sort</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:green">O(n)</td>
  </tr>
  <tr>
    <td>In-place Heap Sort</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:orange">O(n log n)</td>
    <td style="color:lightgreen">O(1)</td>
  </tr>
  <tr>
    <td>Quick Select</td>
    <td style="color:green">O(n)</td>
    <td style="color:red">O(n^2)</td>
    <td style="color:green">O(n)</td>
    <td style="color:lightgreen">O(1)</td>
  </tr>
</table>