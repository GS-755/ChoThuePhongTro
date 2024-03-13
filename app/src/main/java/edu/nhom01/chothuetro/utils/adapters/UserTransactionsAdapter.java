package edu.nhom01.chothuetro.utils.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.utils.DisplayImage;
import edu.nhom01.chothuetro.utils.StrProcessor;

public class UserTransactionsAdapter extends RecyclerView.Adapter<UserTransactionsAdapter.ItemVH> {
    private Context context;
    private ArrayList<Transaction> transactionArrayList;

    public UserTransactionsAdapter() { }
    public UserTransactionsAdapter(Context context) {
        this.context = context;
    }
    public UserTransactionsAdapter(ArrayList<Transaction> transactionArrayList) {
        this.transactionArrayList = transactionArrayList;
    }
    public UserTransactionsAdapter(Context context, ArrayList<Transaction> transactionArrayList) {
        this.context = context;
        this.transactionArrayList = transactionArrayList;
    }

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).
                inflate(R.layout.layout_all_transactions, parent, false);

        return new ItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {
        Transaction item = transactionArrayList.get(position);
        String motelImageUrl = DisplayImage.getMotelImageUrl(item.getMotelId());
        holder.imgTransMotel.setImageBitmap(DisplayImage.getMotelImageBitmap(motelImageUrl));
        holder.labelTransMotelName.setText(item.getMotel().getTitle());
        holder.labelTransPublisher.setText(String.format("Người đăng: @%s", item.getUserName().trim()));
        holder.labelHsTransactId.setText(item.getTransactionId().trim());
        holder.labelTransAmount.setText(StrProcessor.formatVnCurrency(item.getAmount()));
    }

    @Override
    public int getItemCount() {
        return this.transactionArrayList.size();
    }

    public static final class ItemVH extends RecyclerView.ViewHolder {
        private ImageView imgTransMotel;
        private TextView labelHsTransactId;
        private TextView labelTransMotelName, labelTransPublisher;
        private TextView labelTransAmount;

        public ItemVH(@NonNull View itemView) {
            super(itemView);

            this.imgTransMotel = itemView.findViewById(R.id.imgTransMotel);
            this.labelHsTransactId = itemView.findViewById(R.id.labelHsTransactId);
            this.labelTransMotelName = itemView.findViewById(R.id.labelTransMotelName);
            this.labelTransPublisher = itemView.findViewById(R.id.labelTransPublisher);
            this.labelTransAmount = itemView.findViewById(R.id.labelTransAmount);
        }
    }
}
