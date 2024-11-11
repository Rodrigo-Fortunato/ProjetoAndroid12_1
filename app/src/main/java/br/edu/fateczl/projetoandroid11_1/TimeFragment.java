package br.edu.fateczl.projetoandroid11_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 *@author:<Rodrigo Fortunato Martins Neves>
 */
public class TimeFragment extends Fragment {

    private View view;
    private TextView tvResultadoTime;
    public TimeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_time, container, false);
        tvResultadoTime = view.findViewById(R.id.tvResultadoTime);
        tvResultadoTime.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }






}