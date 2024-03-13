package edu.nhom01.chothuetro.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.models.person.User;
import edu.nhom01.chothuetro.models.transactions.Transaction;
import edu.nhom01.chothuetro.utils.Session;
import edu.nhom01.chothuetro.utils.adapters.UserTransactionsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DepositFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DepositFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DepositFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DepositFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DepositFragment newInstance(String param1, String param2) {
        DepositFragment fragment = new DepositFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deposit, container, false);
    }

    ArrayList<Transaction> transactionArrayList;
    UserTransactionsAdapter userTransactionsAdapter;
    RecyclerView rvUserTransactions;

    public void setComponents(@NonNull View view) {
        transactionArrayList = new ArrayList<>();
        userTransactionsAdapter = new UserTransactionsAdapter();
        rvUserTransactions = view.findViewById(R.id.rvUserTransactions);
    }
    public void setTransactionDecoration() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(),
                DividerItemDecoration.VERTICAL);
        rvUserTransactions.setLayoutManager(layoutManager);
        rvUserTransactions.addItemDecoration(itemDecoration);
    }
    public void fetchTransactions() {
        Call<ArrayList<Transaction>> callTransactions = ApiClient.
                getInstance().getRoute().getTransactions();
        callTransactions.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call,
                                   Response<ArrayList<Transaction>> response) {
                Account account = (Account) Session.get("current-account");
                if(response.isSuccessful()) {
                    for(Transaction item : response.body()) {
                        if(item.getUserName().trim().equals(account.getUserName().trim())) {
                            transactionArrayList.add(item);
                        }
                    }
                    userTransactionsAdapter = new
                            UserTransactionsAdapter(getContext(), transactionArrayList);
                    rvUserTransactions.setAdapter(userTransactionsAdapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {
                Log.e("API_ERR", t.getMessage());
            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setComponents(view);
        setTransactionDecoration();
        fetchTransactions();
    }
}