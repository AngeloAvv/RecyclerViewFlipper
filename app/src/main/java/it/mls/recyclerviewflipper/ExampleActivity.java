package it.mls.recyclerviewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import mls.it.recycleviewflipper.R;
import it.mls.recyclerviewflipper.event.RecyclerViewFlipperRequest;

/**
 * Created by Angelo Cassano on 04/06/2015.
 */
public class ExampleActivity extends Activity implements RecyclerViewFlipperRequest<View>, View.OnClickListener {

    private static final int ID_VIEWFLIPPER = R.id.viewFlipper;

    private static final int ID_BTN_PREV = R.id.btnPrev;
    private static final int ID_BTN_NEXT = R.id.btnNext;

    private static final int FIRST_VIEW = R.layout.inflated_first;
    private static final int SECOND_VIEW = R.layout.inflated_second;
    private static final int THIRD_VIEW = R.layout.inflated_third;

    private RecyclerViewFlipper<View> mRecyclerViewFlipper;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

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

        Button btnPrev = (Button) findViewById(ID_BTN_PREV);
        Button btnNext = (Button) findViewById(ID_BTN_NEXT);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case ID_BTN_PREV:
                mRecyclerViewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
                mRecyclerViewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);
                mRecyclerViewFlipper.showPrevious();
                break;

            case ID_BTN_NEXT:
                mRecyclerViewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
                mRecyclerViewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
                mRecyclerViewFlipper.showNext();
                break;
        }
    }
}
