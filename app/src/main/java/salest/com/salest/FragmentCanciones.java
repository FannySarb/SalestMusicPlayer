package salest.com.salest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static salest.com.salest.MainActivity.albums;
import static salest.com.salest.MainActivity.canciones;
import static salest.com.salest.MusicAdapter.mFiles;

public class FragmentCanciones extends Fragment {

    RecyclerView recyclerView;
    static MusicAdapter musicAdapter;

    public FragmentCanciones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_canciones, container, false);
        recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        if(!(canciones.size()<1))
        {
            musicAdapter=new MusicAdapter(getContext(), canciones);
            recyclerView.setAdapter(musicAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        }
        return view;
    }


}
