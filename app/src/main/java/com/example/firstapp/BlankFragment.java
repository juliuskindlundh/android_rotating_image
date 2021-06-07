package com.example.firstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BlankFragment extends Fragment {

    private Button button;
    private static boolean buttonState = false;

    public BlankFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){


        View view = inflater.inflate(R.layout.fragment_blank,container,false);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonState = !buttonState;
                if(buttonState){
                    button.setText(R.string.button_lock);
                }
                else{
                    button.setText(R.string.button_unlock);
                }
            }
        });

        return view;
    }

    public boolean getButtonState(){
        return this.buttonState;
    }


}