/* An ArrayQueue is a queue based on an array. The array is a circular array.
 * The queue should grow dynamically if it gets full (it should double in
 * capacity each time it gets full).
 *
 * The default constructor creates an ArrayQueue that is empty but has some
 * capacity > 0. The copy constructor creates a deep copy of the given
 * ArrayQueue object. This means that it gets its own deep copy of the data.
 *
 * The add method adds an element to the back of the queue. The remove method
 * removes one item from the front of the queue. These methods should not move
 * any data already in the queue. The getFront method returns the item at the
 * front of the queue.
 *
 * The getLength function returns the length of the queue. If the length is 0,
 * the queue is considered to be empty.
 *
 * The copyFrom method first checks to see if the queue we are assigning to is
 * the same as this, and if not, makes a deep copy of the given queue.
 *
 * The doubleCapacity method doubles the capacity of the ArrayQueue, and updates
 * the data members so they are now valid for the newly allocated array.
 *
 * Note that even if some methods are not used in your project, you still need
 * to implement them all correctly!
 */
//Everytime we insert we have to take care to change the
class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;

  private void doubleCapacity() {
    Location[] doubledArray = new Location[capacity*2];
    int newElement = 0; //This will keep track of the latest open slot in  the doubledArray

    for(int element = front; element < capacity; element++){
      //Check for empty slot before copying over.
      if (this.data[element] != null){
        doubledArray[newElement] = this.data[element];
        if(element == front){front = newElement;} //update the front
        newElement++;
      }
    }
    // If we have any elements that are wrapped around...
    if (newElement < this.length){
      for(int element = 0; newElement < this.length; element++){
        if (this.data[element] != null){
          doubledArray[newElement] = this.data[element];
          newElement++;
        }
      }
    }
    this.data = doubledArray;
    this.capacity = 2 * this.capacity;
  }

  ArrayQueue() {
    // -
    this.length = 0;
    this.capacity = 5;
    this.front = 0;
    this.data = new Location[this.capacity];
  }

  ArrayQueue(ArrayQueue q) {
    // -
    //this.length can serve 2 purposes, the length, and the next available position of the array.
    this.data = new Location[q.capacity];
    this.capacity = q.capacity;

    if(this.length < q.length){
      for(int element = q.front; element < q.capacity; element++){
        if (q.data[element] != null){
          this.data[this.length] = q.data[element];
          this.length++;
        }
      }
      if(this.length < q.length){ //If we have any elements that are wrapped around...
        for(int element = 0; this.length < q.length; element++){
          if (q.data[element] != null){
            this.data[this.length] = q.data[element];
            this.length++;
          }
        }
      }
    }
  }

  void add(Location loc) {
    //
    this.data[(this.length+this.front) % this.capacity] = loc;
    this.length++;

    //Check if we need to double capacity
    if(this.length == (this.capacity-2) ){
      this.doubleCapacity();
    }
  }

  void remove() {
    //
    if(this.front == this.capacity-1){
      this.front = 0;
    } else{
      this.front++;
    }
    this.length--;
  }

  Location getFront() {
    // -
    return this.data[front];
  }

  int getLength()  {
    // -
    return this.length;
  }

  ArrayQueue copyFrom(ArrayQueue q) {
  // -
    if(this.data != q.data){
      //this.length can serve 2 purposes, the length, and the next available position of the array.
      this.data = new Location[q.capacity];
      this.capacity = q.capacity;
      this.length = 0;
      this.front = 0;

      if(this.length < q.length){
        for(int element = q.front; element < q.capacity; element++){
          if (q.data[element] != null){
            this.data[this.length] = q.data[element];
            this.length++;
          }
        }
        if(this.length < q.length){ //If we have any elements that are wrapped around...
          for(int element = 0; this.length < q.length; element++){
            if (q.data[element] != null){
              this.data[this.length] = q.data[element];
              this.length++;
            }
          }
        }
      }
      return this;
    }
    return q;
  }
}
