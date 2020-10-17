package salest.com.salest;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

import org.w3c.dom.Text;

import java.io.File;
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

       holder.menuMore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(final View v) {
               PopupMenu popupMenu = new PopupMenu(mContext, v);
               popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
               popupMenu.show();
               //Arreglar boton de borrar
              /* popupMenu.setOnMenuItemClickListener((item) {
                   switch (item.getItemId()){
                       case R.id.delete:
                           Toast.makeText(mContext, "Se elimino", Toast.LENGTH_SHORT).show();
                           deleteFile(position, v);
                           break;
                   }
                   return true;
               });*/

           }
       });
    }

    private void deleteFile(int position, View v)
    {
        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                Long.parseLong(mFiles.get(position).getId()));
        File file = new File(mFiles.get(position).getRuta());
        boolean deleted = file.delete();
        if(deleted) {
            mContext.getContentResolver().delete(contentUri, null, null);
            mFiles.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mFiles.size());
            Snackbar.make(v, "cancion eliminada: ", Snackbar.LENGTH_LONG)
                    .show();
        }
        else
        {
            Snackbar.make(v, "no se pudo eliminar: ", Snackbar.LENGTH_LONG)
                    .show();

        }
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MyVieHolder  extends RecyclerView.ViewHolder{

        TextView nombreCancion;
        ImageView albumArt, menuMore;


        public MyVieHolder(@NonNull View itemView) {
            super(itemView);
            nombreCancion=itemView.findViewById(R.id.nombreCancion);
            albumArt=itemView.findViewById(R.id.portada);
            menuMore=itemView.findViewById(R.id.menuMore);
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
