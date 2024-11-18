package br.edu.fateczl.projetoandroid11_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.projetoandroid11_1.controller.TimeController;
import br.edu.fateczl.projetoandroid11_1.model.Time;
import br.edu.fateczl.projetoandroid11_1.persistence.TimeDAO;

/*
 *@author:<Rodrigo Fortunato Martins Neves>
 */
public class TimeFragment extends Fragment {

    private View view;
    private TextView tvResultadoTime;
    private EditText etCodigoTime, etNomeTime, etCidadeTime;
    private Button btnCadastrarTime, btnDeletarTime, btnListarTime, btnBuscarTime, btnAtualizarTime;
    private TimeController timeController;

    public TimeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_time, container, false);
        tvResultadoTime = view.findViewById(R.id.tvResultadoTime);
        btnCadastrarTime = view.findViewById(R.id.btnCadastrarTime);
        btnDeletarTime = view.findViewById(R.id.btnDeletarTime);
        btnAtualizarTime = view.findViewById(R.id.btnAtualizarTime);
        btnListarTime = view.findViewById(R.id.btnListarTime);
        btnBuscarTime = view.findViewById(R.id.btnBuscarTime);
        etCodigoTime = view.findViewById(R.id.etCodigoTime);
        etCidadeTime = view.findViewById(R.id.etCidadeTime);
        etNomeTime = view.findViewById(R.id.etNomeTime);
        tvResultadoTime.setMovementMethod(new ScrollingMovementMethod());

        timeController = new TimeController(new TimeDAO(view.getContext()));

        btnCadastrarTime.setOnClickListener(op -> acaoInsert());
        btnDeletarTime.setOnClickListener(op -> acaoDelete());
        btnAtualizarTime.setOnClickListener(op -> acaoUpdate());
        btnListarTime.setOnClickListener(op -> acaoFindOne());
        btnBuscarTime.setOnClickListener(op -> acaoFindAll());




        return view;


    }

    private void acaoInsert() {
        Time time = makeTime();
        try {
            timeController.insert(time);
            Toast.makeText(view.getContext(),"Time cadastrado com sucesso.",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        clearFields();
    }

    private void acaoDelete() {
        Time time = makeTime();
        try {
            timeController.delete(time);
            Toast.makeText(view.getContext(),"Time Deletado com sucesso.",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        clearFields();
    }

    private void acaoUpdate() {
        Time time = makeTime();
        try {
            timeController.update(time);
            Toast.makeText(view.getContext(),"Time Atualizado com sucesso.",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        clearFields();
    }

    private void acaoFindOne() {
        Time time = makeTime();

        try {
           time = timeController.findOne(time);
           if (time.getNome() != null){
               fillFields(time);
           }else{
               Toast.makeText(view.getContext(),"Time não encontrado",Toast.LENGTH_SHORT).show();
               clearFields();
           }

        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void acaoFindAll() {
        try {
            List<Time> times = timeController.findALL();
            StringBuilder stringBuilder = new StringBuilder();
            for (Time time: times){
                stringBuilder.append(time.toString()).append("\n");
            }
            tvResultadoTime.setText(stringBuilder.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

        private Time makeTime(){
            Time time = new Time();
            time.setCodigo(Integer.parseInt(etCodigoTime.getText().toString()));
            time.setNome(etNomeTime.getText().toString());
            time.setCidade(etCidadeTime.getText().toString());

            return time;
        }

        private void fillFields(Time time){
            etCodigoTime.setText(time.getCodigo());
            etNomeTime.setText(time.getNome());
            etCidadeTime.setText(time.getCidade());
        }

        private void clearFields(){
            etCodigoTime.setText("");
            etNomeTime.setText("");
            etCidadeTime.setText("");
        }


}