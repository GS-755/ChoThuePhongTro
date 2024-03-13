package edu.nhom01.chothuetro.fragments.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.nhom01.chothuetro.R;
import edu.nhom01.chothuetro.activities.signin.LoginActivity;
import edu.nhom01.chothuetro.models.person.Account;
import edu.nhom01.chothuetro.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    private Account account;
    private Button btnAdProfileLogout;
    private TextView labelAdProfileName, labelAdProfilePhone, labelAdProfileEmail;

    protected void setComponents(@NonNull View view) {
        this.account = new Account();
        try {
            this.account = (Account) Session.get("current-account");
        }
        catch(NullPointerException ex) {
            Log.d("INT_ERR", ex.getMessage());
        }
        this.btnAdProfileLogout = view.findViewById(R.id.btnAdProfileLogout);
        this.labelAdProfileName = view.findViewById(R.id.labelAdProfileName);
        this.labelAdProfilePhone = view.findViewById(R.id.labelAdProfilePhone);
        this.labelAdProfileEmail = view.findViewById(R.id.labelAdProfileEmail);
    }
    protected void setContentProfile() {
        if(this.account != null) {
            this.labelAdProfileName.setText(this.account.getUser().getFullName().trim());
            this.labelAdProfilePhone.setText(this.account.getPhone().trim());
            this.labelAdProfileEmail.setText(this.account.getEmail().trim());
        }
    }
    protected void setActionLogout() {
        this.btnAdProfileLogout.setOnClickListener(e -> {
            Session.remove("current-account");
            Intent i = new Intent(this.getContext(), LoginActivity.class);
            startActivity(i);
            this.getActivity().finish();
            Toast.makeText(this.getContext(), "Đã đăng xuất!",
                    Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setComponents(view);
        setActionLogout();
        setContentProfile();
    }
}