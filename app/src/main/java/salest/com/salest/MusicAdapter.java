package salest.com.salest;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyVieHolder> {

    private Context mContext;
    private ArrayList<ArchivosMusica> mFiles;

    MusicAdapter(Context mContext, ArrayList<ArchivosMusica> mFiles)
    {
        this.mFiles=mFiles;
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.items_musica, parent, false);

        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVieHolder holder, final int position) {

       holder.nombreCancion.setText(mFiles.get(position).getTitulo());
       byte[] image= getAlbumArt(mFiles.get(position).getRuta());
       if(image!=null)
       {
           Glide.with(mContext).asBitmap()
                   .load(image)
                   .into(holder.albumArt);
       }
       else
       {
           Glide.with(mContext)
                   .load(R.drawable.generica)
                   .into(holder.albumArt);
       }
       holder.itemView.setOnClickListener(new View.OnClickListener()
       {

           @Override
           public void onClick(View v)
           {
               Intent intent= new Intent(mContext, PlayerActivity.class);
               intent.putExtra("position", position);
               mContext.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MyVieHolder  extends RecyclerView.ViewHolder{

        TextView nombreCancion;
        ImageView albumArt;


        public MyVieHolder(@NonNull View itemView) {
            super(itemView);
            nombreCancion=itemView.findViewById(R.id.nombreCancion);
            albumArt=itemView.findViewById(R.id.portada);
        }
    }

    private byte[] getAlbumArt(String uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art= retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
