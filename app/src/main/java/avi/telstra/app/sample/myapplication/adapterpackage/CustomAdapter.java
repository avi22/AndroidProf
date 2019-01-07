package avi.telstra.app.sample.myapplication.adapterpackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import  avi.telstra.app.sample.myapplication.R;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import avi.telstra.app.sample.myapplication.datamodel.Rows;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   Context context;
    ArrayList<Rows> rowsArrayList = new ArrayList<Rows>();


    ImageLoader imageLoader ;
    DisplayImageOptions options;
    public CustomAdapter(Context context, ArrayList<Rows> rows) {
        this.context = context;
        rowsArrayList.addAll(rows);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

         options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher_background)
                .cacheInMemory()
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

          }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {



        if(!rowsArrayList.get(position).getDescription().equals("null")) {
            holder.title.setText(rowsArrayList.get(position).getTitle());

        }
        else
        {
            holder.title.setText("No title available");
        }

        if(!rowsArrayList.get(position).getDescription().equals("null")) {
            holder.description.setText(rowsArrayList.get(position).getDescription());

        }
        else
        {
            holder.description.setText("No description available");
        }

        if(!rowsArrayList.get(position).getImageHref().equals("null")) {
            imageLoader.displayImage(rowsArrayList.get(position).getImageHref(), holder.image, options);
        }
        else
        {
            holder.image.setBackgroundResource(R.drawable.ic_launcher_background);
        }

    }


    @Override
    public int getItemCount() {
        return rowsArrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView title , description;
        ImageView image;
        public MyViewHolder(final View itemView) {
            super(itemView);
            // get the reference of item view's
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("RecyclerView", "onClick：" + getAdapterPosition());




                }
            });
        }
    }



}