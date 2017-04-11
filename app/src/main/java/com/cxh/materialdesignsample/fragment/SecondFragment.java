package com.cxh.materialdesignsample.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.activity.BottomSheetDialogView;
import com.cxh.materialdesignsample.activity.MainActivity;


public class SecondFragment extends Fragment {
    private Toolbar mToolbar;
    private View thirdLayout;
    private SwitchCompat switchCompat;
    private CheckBox checkBox;
    private RadioButton radiobutton;
    private SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("其他组件");
        ((MainActivity) getActivity()).initDrawer(mToolbar);

        thirdLayout = view.findViewById(R.id.thirdLayout);
        switchCompat = (SwitchCompat) view.findViewById(R.id.switchCompat);
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        radiobutton = (RadioButton) view.findViewById(R.id.radiobutton);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);

        view.findViewById(R.id.bottomNavigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), BottomNavigationActivity.class));
            }
        });

        view.findViewById(R.id.snackbars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(thirdLayout, "Snackbar Test", Snackbar.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.bottomSheetDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showBottomSheetDialog();
                BottomSheetDialogView.show(getContext());
            }
        });

        view.findViewById(R.id.dialogs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogs();
            }
        });

        view.findViewById(R.id.switchCompatLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCompat.setChecked(!switchCompat.isChecked());
            }
        });

        view.findViewById(R.id.checkboxLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });

        view.findViewById(R.id.radiobuttonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radiobutton.setChecked(!radiobutton.isChecked());
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Snackbar.make(thirdLayout, "progress=" + progress, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private BottomSheetDialog mBottomSheetDialog;

    public void showBottomSheetDialog() {
        View sheetDialogView = View.inflate(getContext(), R.layout.sheet_dialog, null);
        mBottomSheetDialog = new BottomSheetDialog(getContext());
        mBottomSheetDialog.setContentView(sheetDialogView);
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
        mBottomSheetDialog.show();
    }

    public void showDialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
