package thehub.com.drive_by.icons;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import thehub.com.drive_by.R;


class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

  private int[] iconList;

  public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public ViewHolder(ImageView imageView) {
      super(imageView);
      this.imageView = imageView;
    }
  }

  public IconAdapter(int[] iconList) {
    this.iconList = iconList;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ImageView imageView = (ImageView) LayoutInflater
      .from(parent.getContext())
      .inflate(R.layout.icon_layout, parent, false);
    imageView.setAdjustViewBounds(true);
    ViewHolder viewHolder = new ViewHolder(imageView);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.imageView.setImageResource(iconList[position]);
  }

  @Override
  public int getItemCount() {
    return iconList.length;
  }
}
