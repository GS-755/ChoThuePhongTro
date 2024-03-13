package edu.nhom01.chothuetro.utils.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.motels.DetailsActivity;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.utils.DisplayImage;
import edu.nhom01.chothuetro.utils.StrProcessor;

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
        return this.motelArrayList;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public void setMotelArrayList(ArrayList<Motel> motelArrayList) {
        this.motelArrayList = motelArrayList;
    }

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {
        Motel item = this.motelArrayList.get(position);
        String motelImageUrl = DisplayImage.getMotelImageUrl(item.getMotelId());
        holder.imgMotel.setImageBitmap(DisplayImage.getMotelImageBitmap(motelImageUrl));
        holder.labelMotelTitle.setText(item.getTitle());
        holder.labelMotelPublisher.setText(item.getUserName());
        holder.labelMotelArea.setText(
                String.format("%.1f m2", item.getArea())
        );
        holder.labelMotelPrice.setText(
                StrProcessor.formatVnCurrency(item.getFullAmount())
        );
        holder.setItemClickListener((view, position1, isLongClick) -> {
            Intent i = new Intent(this.context, DetailsActivity.class);
            i.putExtra("motel-id", item.getMotelId());
            i.putExtra("motel-publisher", item.getUserName());
            this.context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return this.motelArrayList.size();
    }

    public static final class ItemVH extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ImageView imgMotel;
        private TextView labelMotelTitle, labelMotelPublisher, labelMotelArea;
        private TextView labelMotelPrice;
        private ItemClickListener itemClickListener;

        public ItemVH(@NonNull View itemView) {
            super(itemView);
            this.imgMotel = itemView.findViewById(R.id.imgMotel);
            this.labelMotelTitle = itemView.findViewById(R.id.labelMotelTitle);
            this.labelMotelPublisher = itemView.findViewById(R.id.labelMotelPublisher);
            this.labelMotelArea = itemView.findViewById(R.id.labelMotelArea);
            this.labelMotelPrice = itemView.findViewById(R.id.labelMotelPrice);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, this.getAdapterPosition(), false);
        }
    }
}
