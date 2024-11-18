package br.edu.fateczl.projetoandroid11_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.edu.fateczl.projetoandroid11_1.controller.JogadorController;
import br.edu.fateczl.projetoandroid11_1.controller.TimeController;
import br.edu.fateczl.projetoandroid11_1.model.Jogador;
import br.edu.fateczl.projetoandroid11_1.model.Time;
import br.edu.fateczl.projetoandroid11_1.persistence.JogadorDAO;
import br.edu.fateczl.projetoandroid11_1.persistence.TimeDAO;

/*
 *@author:<Rodrigo Fortunato Martins Neves>
 */
public class JogadorFragment extends Fragment {

    private View view;
    private TextView tvResultadoJogador;
    private EditText etIdJogador, etNomeJogador, etDataNascJogador,etAlturaJogador,etPesoJogador;
    private Button btnCadastrarJogador, btnDeletarJogador, btnListarJogador, btnBuscarJogador, btnAtualizarJogador;
    private Spinner spTime;

    private TimeController timeController;
    private JogadorController jogadorController;
    private List<Time> times;
   

    public JogadorFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jogador, container, false);

        tvResultadoJogador = view.findViewById(R.id.tvResultadoJogador);
        tvResultadoJogador.setMovementMethod(new ScrollingMovementMethod());
        btnCadastrarJogador = view.findViewById(R.id.btnCadastrarJogador);
        btnDeletarJogador = view.findViewById(R.id.btnDeletarJogador);
        btnAtualizarJogador = view.findViewById(R.id.btnAtualizarJogador);
        btnListarJogador = view.findViewById(R.id.btnListarJogador);
        btnBuscarJogador = view.findViewById(R.id.btnBuscarJogador);
        etIdJogador = view.findViewById(R.id.etIdJogador);
        etNomeJogador = view.findViewById(R.id.etNomeJogador);
        etDataNascJogador = view.findViewById(R.id.etDataNascJogador);
        etAlturaJogador = view.findViewById(R.id.etAlturaJogador);
        etPesoJogador = view.findViewById(R.id.etPesoJogador);
        spTime = view.findViewById(R.id.spTime);

        timeController = new TimeController(new TimeDAO(view.getContext()));
        jogadorController = new JogadorController(new JogadorDAO(view.getContext()));
        fillSpinner();

        btnCadastrarJogador.setOnClickListener(op -> acaoInsert());
        btnDeletarJogador.setOnClickListener(op -> acaoDelete());
        btnAtualizarJogador.setOnClickListener(op -> acaoUpdate());
        btnListarJogador.setOnClickListener(op -> acaoFindOne());
        btnBuscarJogador.setOnClickListener(op -> acaoFindAll());


        return view;
    }
    private void acaoInsert() {
        int spPos = spTime.getSelectedItemPosition();
        if (spPos > 0) {

            Jogador jogador = makeJogador();
            try {
                jogadorController.insert(jogador);
                Toast.makeText(view.getContext(), "Jogador cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            clearFields();

        }else {
            Toast.makeText(view.getContext(), "Selecione um Time", Toast.LENGTH_SHORT).show();
        }
    }

    private void acaoDelete() {
        Jogador jogador = makeJogador();
        try {
            jogadorController.delete(jogador);
            Toast.makeText(view.getContext(),"Jogador Deletado com sucesso.",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        clearFields();
    }

    private void acaoUpdate() {
        int spPos = spTime.getSelectedItemPosition();
        if (spPos > 0) {

            Jogador jogador = makeJogador();
            try {
                jogadorController.update(jogador);
                Toast.makeText(view.getContext(), "Jogador atualizado com sucesso.", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            clearFields();

        }else {
            Toast.makeText(view.getContext(), "Selecione um Time", Toast.LENGTH_SHORT).show();
        }
    }

    private void acaoFindOne() {
        Jogador jogador = makeJogador();

        try {
            times = timeController.findALL();
            jogador = jogadorController.findOne(jogador);
            jogador = jogadorController.findOne(jogador);
            if (jogador.getNome() != null){
                fillFields(jogador);
            }else{
                Toast.makeText(view.getContext(),"Jogador não encontrado",Toast.LENGTH_SHORT).show();
                clearFields();
            }

        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void acaoFindAll() {
        try {
            List<Jogador> jogadores = jogadorController.findALL();
            StringBuilder stringBuilder = new StringBuilder();
            for (Jogador jogador : jogadores) {
                stringBuilder.append(jogador.toString()).append("\n");
            }
            tvResultadoJogador.setText(stringBuilder.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private Jogador makeJogador(){
        Jogador jogador = new Jogador();

        jogador.setId(Integer.parseInt(etIdJogador.getText().toString()));
        jogador.setNome(etNomeJogador.getText().toString());
        jogador.setDataNasc(LocalDate.parse(etDataNascJogador.getText().toString()));
        jogador.setAltura(Float.parseFloat(etAlturaJogador.getText().toString()));
        jogador.setPeso(Float.parseFloat(etPesoJogador.getText().toString()));
        jogador.setTime((Time) spTime.getSelectedItem());


        return jogador;
    }

    private void fillFields(Jogador jogador){
        etIdJogador.setText(jogador.getId());
        etNomeJogador.setText(jogador.getNome());
        etDataNascJogador.setText(String.valueOf(jogador.getDataNasc()));
        etAlturaJogador.setText((int) jogador.getAltura());
        etPesoJogador.setText((int) jogador.getPeso());

        int cont =0;
        for (Time t: times){
            if (t.getCodigo() == jogador.getTime().getCodigo()){
                spTime.setSelection(cont);
            }else{
                cont++;
            }
        }
        if (cont > times.size()){
            spTime.setSelection(0);
        }

    }

    private void clearFields(){
        etIdJogador.setText("");
        etNomeJogador.setText("");
        etDataNascJogador.setText("");
        etAlturaJogador.setText("");
        etPesoJogador.setText("");
    }

        private void fillSpinner() {
        Time time = new Time();
        time.setCodigo(0);
        time.setNome("");
        time.setCidade("");

        try {
            times = timeController.findALL();
            times.add(0,time);

            ArrayAdapter<Time> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item,times);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTime.setAdapter(adapter);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }
}