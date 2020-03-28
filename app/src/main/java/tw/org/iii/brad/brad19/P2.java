package tw.org.iii.brad.brad19;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;



public class P2 extends Fragment {
    private View mainView;
    private ViewFlipper flipper;    //見xml中的ViewFlipper

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mainView == null){   //才不會從別頁面回來時又重置畫面上的東西
            mainView = inflater.inflate(R.layout.fragment_p2, container, false);
            flipper = mainView.findViewById(R.id.viewFlipper);
            MyFlipperListener myFlipperListener = new MyFlipperListener();
            View f1 = flipper.getChildAt(0);
            View f2 = flipper.getChildAt(1);
            View f3 = flipper.getChildAt(2);
            f1.setOnClickListener(myFlipperListener);
            f2.setOnClickListener(myFlipperListener);
            f3.setOnClickListener(myFlipperListener);
        }
        return mainView;
    }

    private class MyFlipperListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            flipper.showNext();
        }
    }

}
