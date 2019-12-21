package id.slametriyadi.sqlite_imastudio.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.slametriyadi.sqlite_imastudio.AddUpdateActivity;
import id.slametriyadi.sqlite_imastudio.R;
import id.slametriyadi.sqlite_imastudio.model.Nota;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.ViewHolder>{

    private ArrayList<Nota> listNota;
    private Activity activity;
    ArrayList<Nota> getListNota() { return listNota;}

    public NotaAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListNota(ArrayList<Nota> listNota) {
        this.listNota = listNota;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_nota, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTanggal.setText(getListNota().get(position).getTanggal());
        holder.tvJudul.setText(getListNota().get(position).getJudul());
        holder.tvDeskripsi.setText(getListNota().get(position).getDeskripsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddUpdateActivity.class);
                intent.putExtra(AddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(AddUpdateActivity.EXTRA_NOTA, getListNota().get(position));
                activity.startActivityForResult(intent, AddUpdateActivity.REQUEST_UPDATE);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listNota.size() > 0) {
            return listNota.size();
        }
        return listNota.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvJudul, tvTanggal, tvDeskripsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }
}
