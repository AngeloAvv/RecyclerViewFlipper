package it.mls.recyclerviewflipper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewFlipper;

import java.util.List;

import it.mls.recyclerviewflipper.event.RecyclerViewFlipperRequest;
import it.mls.recyclerviewflipper.exception.NotAnOddNumberException;
import it.mls.recyclerviewflipper.model.ViewFlipperQueue;

/**
 * Created by Angelo Cassano on 04/06/2015.
 *
 * The RecyclerViewFlipper is a Custom {@see ViewFlipper} that allows you to scroll between an infinite number
 * of Views without render-loss time. The component stores a double-ended queue (deque) in order to
 * pre-load the views that need to be shown. In this way, you could dinamically add an infinite (we suppose to!)
 * odd number of views without wasting your time in pre-rendering phases while scrolling.
 * The RecyclerViewFlipper also supports animations as its superclass {@see ViewFlipper}, and distorts the way of
 * seeing the views since they are handled as a stack, but stored as a deque.
 */
public class RecyclerViewFlipper<T extends View> extends ViewFlipper {

    private static final String NP_ERROR = "You must initialize the queue with some views";
    private static final String LS_ERROR = "Please call setRecycleViewFlipperRequest(RecycleViewFlipperRequest<T> request) in order to set a correct listener";

    private RecyclerViewFlipperRequest<T> mRequest;
    private ViewFlipperQueue<T> mQueue;

    public RecyclerViewFlipper(Context context) {
        super(context);
    }

    public RecyclerViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * This methods initializes the internal deque of the RecyclerViewFlipper.
     * Make sure that the {@see List} is composed of an odd number of items greater than 1. Remember that
     * this number will be the final size of the deque which will contain the pre-loaded views
     * @param views A {@see List} of views that needs to be added inside the {@see RecyclerViewFlipper}
     * @throws NotAnOddNumberException Throws an exception if you don't put an odd number of items, or
     * a number of items smaller than 1
     */
    public void initializeComponents(List<T> views) throws NotAnOddNumberException {
        if(views.size() <= 1 || views.size() % 2 == 0) throw new NotAnOddNumberException(NotAnOddNumberException.ERROR);

        mQueue = new ViewFlipperQueue<>();
        for(T item : views) {
            mQueue.add(item);
            addView(item);
        }

        showMiddle();
    }

    @Override
    public void showNext() {
        if(mRequest == null) throw new IllegalStateException(LS_ERROR);
        if(mQueue == null) throw new NullPointerException(NP_ERROR);

        T item = mRequest.requestLeftItem();
        T first = mQueue.removeFirst();
        removeView(first);

        mQueue.addLast(item);
        addView(item);

        showMiddle();
    }

    @Override
    public void showPrevious() {
        if(mRequest == null) throw new IllegalStateException(LS_ERROR);
        if(mQueue == null) throw new NullPointerException(NP_ERROR);

        T item = mRequest.requestRightItem();
        T last = mQueue.removeLast();
        removeView(last);

        mQueue.addFirst(item);
        addView(item);

        showMiddle();
    }

    /**
     * Sets the {@see RecycleViewFlipperRequest} that will be triggered when the [@see RecycleViewFlipper]
     * scrolls.
     * @param request The event to attach
     */
    public void setRecycleViewFlipperRequest(RecyclerViewFlipperRequest<T> request) {
        this.mRequest = request;
    }

    private void showMiddle() {
        T middle = mQueue.middle();
        int indexChild = indexOfChild(middle);
        setDisplayedChild(indexChild);
    }
}
