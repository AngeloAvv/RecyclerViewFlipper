package it.mls.recyclerviewflipper.event;

import android.view.View;

/**
 * Created by Angelo Cassano on 04/06/2015.
 */
public interface RecyclerViewFlipperRequest<T extends View> {
    /**
     * This method will be called when the {@see RecycleViewFlipper} requests a new [@see View}
     * to add inside the deque. The item will be put on the top of the deque
     * @return The view to be added
     */
    public T requestLeftItem();
    /**
     * This method will be called when the {@see RecycleViewFlipper} requests a new [@see View}
     * to add inside the deque. The item will be put on the bottom of the deque
     * @return The view to be added
     */
    public T requestRightItem();
}
