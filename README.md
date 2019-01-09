# RecyclerViewFlipper
A custom ViewFlipper to dynamically add views at runtime without render-loss time

The <b>RecyclerViewFlipper</b> is a Custom <b>ViewFlipper</b> that allows you to scroll between an infinite number of <b>Views</b> without render-loss time. The component stores a <b>double-ended queue</b> (deque) in order to pre-load the views that need to be shown. In this way, you could <b>dinamically</b> add an infinite (we suppose to!) odd number of views without wasting your time in pre-rendering phases while scrolling.
The RecyclerViewFlipper also supports <b>animations</b> as its superclass ViewFlipper, and distorts the way of seeing the views since they are handled as a <b>stack</b>, but stored as a deque.
This library also avoids OutOfMemory errors due to a lot of views loaded inside the ViewFlipper, because in this case the views are added at runtime with a fixed number of loaded views per time.

## How to use it

In your layout:

        <it.mls.recyclerviewflipper.RecyclerViewFlipper
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:layout_below="@+id/buttonContainer"
          android:id="@+id/viewFlipper" >
        </it.mls.recyclerviewflipper.RecyclerViewFlipper>

and in your Java file:

        View firstView = View.inflate(this, FIRST_VIEW, null);
        View secondView = View.inflate(this, SECOND_VIEW, null);
        View thirdView = View.inflate(this, THIRD_VIEW, null);

        ArrayList<View> views = new ArrayList<>();
        views.add(firstView);
        views.add(secondView);
        views.add(thirdView);

        mRecyclerViewFlipper = (RecyclerViewFlipper<View>) findViewById(ID_VIEWFLIPPER);
        mRecyclerViewFlipper.initializeComponents(views);
        mRecyclerViewFlipper.setRecycleViewFlipperRequest(this);

your activity must implement <code>RecyclerViewFlipperRequest\<T></code> , where T could be a class that inherits from View and these two methods deal the runtime generation of the dynamic view

        @Override
        public View requestLeftItem() {
          View newView = View.inflate(this, SECOND_VIEW, null);
          Button btn = (Button) newView.findViewById(R.id.btn);
          btn.setText(String.valueOf(mCounter++));
        
          return newView;
        }

        @Override
        public View requestRightItem() {
          View newView = View.inflate(this, SECOND_VIEW, null);
          Button btn = (Button) newView.findViewById(R.id.btn);
          btn.setText(String.valueOf(mCounter--));
        
          return newView;
        }

In this example I've inflated the same view changing the content of the button, but you can do it in very different ways, like putting it inside a Stack while you process it and so on.
Keep in mind that the preprocessing deque is defined when you call <code>mRecyclerViewFlipper.initializeComponents(views);</code>
the queue will keep a number of K views per time, where K is the size of the List

If you want to see an example of the library in action, please [watch a demo here](https://youtu.be/jqfqNujkTfo)
