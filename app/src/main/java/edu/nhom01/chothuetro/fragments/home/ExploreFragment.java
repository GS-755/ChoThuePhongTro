package edu.nhom01.chothuetro.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.models.motels.Motel;
import edu.nhom01.chothuetro.utils.Session;
import edu.nhom01.chothuetro.utils.adapters.ExploreMotelsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
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
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    ArrayList<Motel> motelArrayList;
    ExploreMotelsAdapter exploreMotelsAdapter;
    RecyclerView rvExploreMotels;
    public void setComponents(@NonNull View view) {
        motelArrayList = new ArrayList<>();
        exploreMotelsAdapter = new ExploreMotelsAdapter();
        rvExploreMotels = view.findViewById(R.id.rvExploreAllMotels);
    }
    public void setExploreMotelsDecoration() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(),
                DividerItemDecoration.VERTICAL);
        rvExploreMotels.setLayoutManager(layoutManager);
        rvExploreMotels.addItemDecoration(itemDecoration);
    }
    public void fetchContentMotels() {
        motelArrayList = (ArrayList<Motel>) Session.get("motels-data");
        exploreMotelsAdapter = new ExploreMotelsAdapter(getContext(), motelArrayList);
        rvExploreMotels.setAdapter(exploreMotelsAdapter);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setComponents(view);
        setExploreMotelsDecoration();
        fetchContentMotels();
    }
}