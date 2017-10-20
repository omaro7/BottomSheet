package kr.co.goms.ui.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SampleActivity extends AppCompatActivity {

    LinearLayout mFltBottomSheet;
    BottomSheetBehavior mBehavior;

    Button mBtn;
    FloatingActionButton mFabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mBtn = findViewById(R.id.btn_open_bottomsheet);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheet();
            }
        });

        mFltBottomSheet = findViewById(R.id.bottom_sheet);
        mFabBtn = findViewById(R.id.fab);
        mBehavior = BottomSheetBehavior.from(mFltBottomSheet);

        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                /*
                newState = 상태값
                BottomSheetBehavior.STATE_COLLAPSED: 기본적인 상태이며, 일부분의 레이아웃만 보여지고 있는 상태. 이 높이는 behavior_peekHeight속성을 통해 변경 가능
                BottomSheetBehavior.STATE_DRAGGING: 드래그중인 상태
                BottomSheetBehavior.STATE_SETTLING: 드래그후 완전히 고정된 상태
                BottomSheetBehavior.STATE_EXPANDED: 확장된 상태
                BottomSheetBehavior.STATE_HIDDEN: 기본적으로 비활성화 상태이며, app:behavior_hideable을 사용하는 경우 완전히 숨겨져 있는 상태
                 */

                if(newState == BottomSheetBehavior.STATE_DRAGGING) {
                    mBtn.setVisibility(View.GONE);
                }else if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    mBtn.setVisibility(View.GONE);
                }else{
                    mBtn.setVisibility(View.VISIBLE);
                }


            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }



        });

        mFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openBottomSheet() {
        if(mFltBottomSheet.getVisibility() == View.VISIBLE){
            mFltBottomSheet.setVisibility(View.GONE);
            CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) mFabBtn.getLayoutParams();
            p.setAnchorId(View.NO_ID);
            mFabBtn.setLayoutParams(p);
            mFabBtn.setVisibility(View.GONE);
        }else{
            mFltBottomSheet.setVisibility(View.VISIBLE);
            CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) mFabBtn.getLayoutParams();
            p.setAnchorId(mFltBottomSheet.getId());
            mFabBtn.setLayoutParams(p);
            mFabBtn.setVisibility(View.VISIBLE);
        }
    }


}
