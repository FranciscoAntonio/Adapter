package com.example.nti_05.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CarroAdapter extends BaseAdapter{

    Context ctx;
    List<Carro> carros;

    public CarroAdapter(Context ctx, List<Carro> carros){
        this.ctx = ctx;
        this.carros = carros;
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int position) {
        return carros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //É chamado para cada objeto da lista.Retorna cada linha de informação.
    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Carro carro = carros.get(position);
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_carro, null);
        ImageView imgLogo = (ImageView) linha.findViewById(R.id.imgLogo);
        TextView txtModelo = (TextView) linha.findViewById(R.id.txtModelo);
        TextView txtAno = (TextView) linha.findViewById(R.id.txtAno);
        TextView txtCombustivel = (TextView) linha.findViewById(R.id.txtCombustivel);

        Resources res = ctx.getResources();

        TypedArray logos = res.obtainTypedArray(R.array.logos);
        imgLogo.setImageDrawable(logos.getDrawable(carro.fabricante));

        txtModelo.setText(carro.modelo);
        txtAno.setText(String.valueOf(carro.ano));
        txtCombustivel.setText(
                (carro.gasolina ? "G" : "") +
                        (carro.etanol ? "E" : "")
        );
        return linha;
    }*/
    //Em uma situação onde há muitas linhas a serem retornadas o método acima pode ser um problema.
    //Para otimizar o processo segue o código abaixo.
    /* O android não cria uma lista por inteiro. De acordo com a rolagem da
     lista ele solicita a criação de novas linhas e consequentemente de novas Views.
    O código abaixo reaproveita as linhas e as views criadas ao invés de solicitar novas linhas e novas views.*/

    static class ViewHolder{
        ImageView imgLogo;
        TextView txtModelo;
        TextView txtAno;
        TextView txtCombustivel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Carro carro = carros.get(position);

        ViewHolder holder = null;
        if(convertView == null){ //Nesse momento a View ainda não existe então deve ser criada.
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_carro, null);// Carregamos o layout.
            holder = new ViewHolder();//Instancia do ViewHolder.
            /*Em cada atributo dele armazenamos a referencia dos componentes contidos no arquivo de layout.*/
            holder.imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);
            holder.txtModelo = (TextView) convertView.findViewById(R.id.txtModelo);
            holder.txtAno = (TextView) convertView.findViewById(R.id.txtAno);
            holder.txtCombustivel = (TextView) convertView.findViewById(R.id.txtCombustivel);
            convertView.setTag(holder);//Se a view pode ser reaproveitada pegamos o ViewHolder a partir do método getTag
            // e ele já terá a referencia de todos os componentes.
        }else{ //View reutilizada, pegamos apenas as informações.
            holder = (ViewHolder) convertView.getTag();
        }
        Resources res = ctx.getResources();

        TypedArray logos = res.obtainTypedArray(R.array.logos);
        holder.imgLogo.setImageDrawable(logos.getDrawable(carro.fabricante));

        holder.txtModelo.setText(carro.modelo);
        holder.txtAno.setText(String.valueOf(carro.ano));
        holder.txtCombustivel.setText(
                (carro.gasolina ? "G" : "") +
                        (carro.etanol ? "E" : "")
        );
        return convertView;
    }
}
