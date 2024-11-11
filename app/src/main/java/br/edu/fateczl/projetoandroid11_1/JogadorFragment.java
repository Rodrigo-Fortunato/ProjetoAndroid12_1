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
public class JogadorFragment extends Fragment {

    private View view;
    private TextView tvResultadoJogador;

    public JogadorFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jogador, container, false);

        tvResultadoJogador = view.findViewById(R.id.tvResultadoJogador);
        tvResultadoJogador.setMovementMethod(new ScrollingMovementMethod());


        return view;
    }
}