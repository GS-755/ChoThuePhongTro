package edu.nhom01.chothuetro.fragments.widgets.utils.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.nhom01.chothuetro.models.motels.Motel;

public class MotelsAdapter extends RecyclerView.Adapter<MotelsAdapter.ItemVH> {
    private Context context;
    private ArrayList<Motel> motelArrayList;

    public MotelsAdapter(ArrayList<Motel> motelArrayList) {
        this.motelArrayList = motelArrayList;
    }
    public MotelsAdapter(Context context, ArrayList<Motel> motelArrayList) {
        this.context = context;
        this.motelArrayList = motelArrayList;
    }

    public Context getContext() { return this.context; }
    public ArrayList<Motel> getMotelArrayList() {
        return motelArrayList;
    }

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static final class ItemVH extends RecyclerView.ViewHolder {

        public ItemVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
