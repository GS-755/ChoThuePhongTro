package edu.nhom01.chothuetro.fragments.admin;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.admin.motels.AddMotelActivity;
import edu.nhom01.chothuetro.api.client.ApiClient;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.utils.Session;
import edu.nhom01.chothuetro.utils.adapters.DashboardMotelsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
    Button btnAddMotel;
    ArrayList<Motel> motelArrayList;
    DashboardMotelsAdapter dashboardMotelsAdapter;
    TextView labelDashboardAccount;
    Account account;
    RecyclerView rvDashboardMotels;

    protected void setComponents(@NonNull View view) {
        motelArrayList = new ArrayList<>();
        account = (Account) Session.get("current-account");
        labelDashboardAccount = view.findViewById(R.id.labelDashboardAccount);
        labelDashboardAccount.setText(String.format("@%s", account.getUserName()));
        btnAddMotel = view.findViewById(R.id.btnAddMotel);
        rvDashboardMotels = view.findViewById(R.id.rvDashboardMotels);
    }
    protected void setActionAddMotel() {
        btnAddMotel.setOnClickListener(e -> {
            Intent i = new Intent(this.getContext(), AddMotelActivity.class);
            startActivity(i);
        });
    }
    public void setContentDashboardMotels() {
        Call<ArrayList<Motel>> callMotel = ApiClient.
                getInstance().getRoute().getMotels();
        callMotel.enqueue(new Callback<ArrayList<Motel>>() {
            @Override
            public void onResponse(Call<ArrayList<Motel>> call, Response<ArrayList<Motel>> response) {
                if(response.isSuccessful()) {
                    for(Motel item : response.body()) {
                        if(item.getUserName().trim().equals(account.getUserName().trim())) {
                            motelArrayList.add(item);
                        }
                    }
                    dashboardMotelsAdapter = new DashboardMotelsAdapter(getContext(), motelArrayList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false);
                    DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
                            DividerItemDecoration.VERTICAL);
                    rvDashboardMotels.setLayoutManager(layoutManager);
                    rvDashboardMotels.addItemDecoration(itemDecoration);
                    rvDashboardMotels.setAdapter(dashboardMotelsAdapter);
                    dashboardMotelsAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Motel>> call, Throwable t) {
                Log.e("API_ERR", t.getMessage());
            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setComponents(view);
        setContentDashboardMotels();
        setActionAddMotel();
    }
}