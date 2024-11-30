package com.google.appinventor.components.runtime;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.runtime.util.YailDictionary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListAdapterWithRecyclerView extends RecyclerView.Adapter<RvViewHolder> implements Filterable {
    private static final String LOG_TAG = "ListAdapterRecyclerView";
    private int backgroundColor;
    /* access modifiers changed from: private */
    public ClickListener clickListener;
    protected final ComponentContainer container;
    protected final Filter filter = new Filter() {
        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String filterString;
            ListAdapterWithRecyclerView.this.lastQuery = charSequence.toString().toLowerCase();
            Filter.FilterResults results = new Filter.FilterResults();
            List<Object> filteredList = new ArrayList<>();
            ListAdapterWithRecyclerView.this.originalPositions = new ArrayList();
            if (ListAdapterWithRecyclerView.this.lastQuery == null || ListAdapterWithRecyclerView.this.lastQuery.length() == 0) {
                filteredList = new ArrayList<>(ListAdapterWithRecyclerView.this.originalItems);
                ListAdapterWithRecyclerView.this.items = new ArrayList(ListAdapterWithRecyclerView.this.originalItems);
            } else {
                for (int index = 0; index < ListAdapterWithRecyclerView.this.originalItems.size(); index++) {
                    Object item = ListAdapterWithRecyclerView.this.originalItems.get(index);
                    if (!(item instanceof YailDictionary)) {
                        filterString = item.toString();
                    } else if (((YailDictionary) item).containsKey(Component.LISTVIEW_KEY_MAIN_TEXT)) {
                        Object o = ((YailDictionary) item).get(Component.LISTVIEW_KEY_DESCRIPTION);
                        filterString = ((YailDictionary) item).get(Component.LISTVIEW_KEY_MAIN_TEXT).toString();
                        if (o != null) {
                            filterString = filterString + " " + o.toString();
                        }
                    } else {
                        filterString = item.toString();
                    }
                    if (filterString.toLowerCase().contains(ListAdapterWithRecyclerView.this.lastQuery)) {
                        filteredList.add(item);
                        ListAdapterWithRecyclerView.this.originalPositions.add(Integer.valueOf(index));
                    }
                }
            }
            results.count = filteredList.size();
            results.values = filteredList;
            return results;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            ListAdapterWithRecyclerView.this.items = new ArrayList((List) filterResults.values);
            ListAdapterWithRecyclerView.this.clearSelections();
            ListAdapterWithRecyclerView.this.notifyDataSetChanged();
        }
    };
    private int imageHeight;
    private int imageWidth;
    /* access modifiers changed from: private */
    public List<Object> items = new ArrayList();
    /* access modifiers changed from: private */
    public String lastQuery = "";
    private int layoutType;
    /* access modifiers changed from: private */
    public List<Object> originalItems = new ArrayList();
    /* access modifiers changed from: private */
    public List<Integer> originalPositions = new ArrayList();
    private float radius;
    private List<Integer> selectedItems = new ArrayList();
    private int selectionColor;
    private int textDetailColor;
    private String textDetailFont;
    private float textDetailSize;
    private int textMainColor;
    private String textMainFont;
    private float textMainSize;

    public interface ClickListener {
        void onItemClick(int i, View view);
    }

    public ListAdapterWithRecyclerView(ComponentContainer container2, List<Object> data, int layoutType2, int textMainColor2, int textDetailColor2, float textMainSize2, float textDetailSize2, String textMainFont2, String textDetailFont2, int backgroundColor2, int selectionColor2, int imageWidth2, int imageHeight2, int radius2) {
        this.container = container2;
        this.layoutType = layoutType2;
        this.textMainColor = textMainColor2;
        this.textDetailColor = textDetailColor2;
        this.textMainSize = textMainSize2;
        this.textDetailSize = textDetailSize2;
        this.textMainFont = textMainFont2;
        this.textDetailFont = textDetailFont2;
        this.backgroundColor = backgroundColor2;
        this.selectionColor = selectionColor2;
        this.imageWidth = imageWidth2;
        this.imageHeight = imageHeight2;
        this.radius = (float) radius2;
        updateData(data);
    }

    public void updateData(List<Object> newItems) {
        this.originalItems = newItems;
        if (this.originalPositions.isEmpty()) {
            this.items = new ArrayList(newItems);
        } else {
            this.filter.filter(this.lastQuery);
        }
        clearSelections();
    }

    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int idImages;
        int idSecond;
        CardView cardView = new CardView(this.container.$context());
        cardView.setContentPadding(15, 15, 15, 15);
        cardView.setPreventCornerOverlap(false);
        cardView.setMaxCardElevation(3.0f);
        cardView.setCardBackgroundColor(this.backgroundColor);
        cardView.setRadius(this.radius);
        cardView.setCardElevation(2.1f);
        ViewCompat.setElevation(cardView, 20.0f);
        cardView.setClickable(true);
        int idCard = ViewCompat.generateViewId();
        cardView.setId(idCard);
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(-1, -2);
        params1.setMargins(0, 0, 0, 0);
        TextView textViewFirst = new TextView(this.container.$context());
        int idFirst = ViewCompat.generateViewId();
        textViewFirst.setId(idFirst);
        textViewFirst.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textViewFirst.setTextSize(this.textMainSize);
        textViewFirst.setTextColor(this.textMainColor);
        TextViewUtil.setFontTypeface(this.container.$form(), textViewFirst, this.textMainFont, false, false);
        LinearLayout linearLayout1 = new LinearLayout(this.container.$context());
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout1.setOrientation(0);
        int i = this.layoutType;
        if (i == 4 || i == 3) {
            ImageView imageView = new ImageView(this.container.$context());
            int idImages2 = ViewCompat.generateViewId();
            imageView.setId(idImages2);
            LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(this.imageWidth, this.imageHeight);
            layoutParamsImage.setMargins(0, 0, 15, 0);
            imageView.setLayoutParams(layoutParamsImage);
            linearLayout1.setGravity(16);
            linearLayout1.addView(imageView);
            idImages = idImages2;
        } else {
            idImages = -1;
        }
        int i2 = this.layoutType;
        if (i2 == 0 || i2 == 3) {
            linearLayout1.addView(textViewFirst);
            idSecond = -1;
        } else {
            TextView textViewSecond = new TextView(this.container.$context());
            int idSecond2 = ViewCompat.generateViewId();
            textViewSecond.setId(idSecond2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            textViewSecond.setTextSize(this.textDetailSize);
            TextViewUtil.setFontTypeface(this.container.$form(), textViewSecond, this.textDetailFont, false, false);
            textViewSecond.setTextColor(this.textDetailColor);
            int i3 = this.layoutType;
            if (i3 == 1 || i3 == 4) {
                textViewSecond.setLayoutParams(layoutParams2);
                LinearLayout linearLayout2 = new LinearLayout(this.container.$context());
                idSecond = idSecond2;
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
                linearLayout2.setOrientation(1);
                linearLayout2.addView(textViewFirst);
                linearLayout2.addView(textViewSecond);
                linearLayout1.addView(linearLayout2);
            } else if (i3 == 2) {
                layoutParams2.setMargins(50, 0, 0, 0);
                textViewSecond.setLayoutParams(layoutParams2);
                textViewSecond.setMaxLines(1);
                textViewSecond.setEllipsize((TextUtils.TruncateAt) null);
                linearLayout1.addView(textViewFirst);
                linearLayout1.addView(textViewSecond);
                idSecond = idSecond2;
            } else {
                idSecond = idSecond2;
            }
        }
        cardView.setLayoutParams(params1);
        cardView.addView(linearLayout1);
        return new RvViewHolder(cardView, idCard, idFirst, idSecond, idImages);
    }

    public void onBindViewHolder(RvViewHolder holder, int position) {
        Object o = this.items.get(position);
        YailDictionary dictItem = new YailDictionary();
        if (!(o instanceof YailDictionary)) {
            dictItem.put(Component.LISTVIEW_KEY_MAIN_TEXT, o.toString());
        } else if (((YailDictionary) o).containsKey(Component.LISTVIEW_KEY_MAIN_TEXT)) {
            dictItem = (YailDictionary) o;
        } else {
            dictItem.put(Component.LISTVIEW_KEY_MAIN_TEXT, o.toString());
        }
        String first = dictItem.get(Component.LISTVIEW_KEY_MAIN_TEXT).toString();
        String second = "";
        if (dictItem.containsKey(Component.LISTVIEW_KEY_DESCRIPTION)) {
            second = dictItem.get(Component.LISTVIEW_KEY_DESCRIPTION).toString();
        }
        String imageName = "";
        if (dictItem.containsKey(Component.LISTVIEW_KEY_IMAGE)) {
            imageName = dictItem.get(Component.LISTVIEW_KEY_IMAGE).toString();
        }
        int i = this.layoutType;
        if (i == 0) {
            holder.textViewFirst.setText(first);
        } else if (i == 1) {
            holder.textViewFirst.setText(first);
            holder.textViewSecond.setText(second);
        } else if (i == 2) {
            holder.textViewFirst.setText(first);
            holder.textViewSecond.setText(second);
        } else if (i == 3) {
            Drawable drawable = new BitmapDrawable();
            try {
                drawable = MediaUtil.getBitmapDrawable(this.container.$form(), imageName);
            } catch (IOException ioe) {
                Log.e(LOG_TAG, "onBindViewHolder Unable to load image " + imageName + ": " + ioe.getMessage());
            }
            holder.textViewFirst.setText(first);
            ViewUtil.setImage(holder.imageVieww, drawable);
        } else if (i == 4) {
            Drawable drawable2 = new BitmapDrawable();
            try {
                drawable2 = MediaUtil.getBitmapDrawable(this.container.$form(), imageName);
            } catch (IOException ioe2) {
                Log.e(LOG_TAG, "onBindViewHolder Unable to load image " + imageName + ": " + ioe2.getMessage());
            }
            holder.textViewFirst.setText(first);
            holder.textViewSecond.setText(second);
            ViewUtil.setImage(holder.imageVieww, drawable2);
        } else {
            Log.e(LOG_TAG, "onBindViewHolder Layout not recognized: " + i);
        }
        if (this.selectedItems.contains(Integer.valueOf(position))) {
            holder.cardView.setCardBackgroundColor(this.selectionColor);
        } else {
            holder.cardView.setCardBackgroundColor(this.backgroundColor);
        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void toggleSelection(int position) {
        if (!this.originalPositions.isEmpty()) {
            position = this.originalPositions.indexOf(Integer.valueOf(position));
        }
        if (!this.selectedItems.contains(Integer.valueOf(position))) {
            if (!this.selectedItems.isEmpty()) {
                int oldPosition = this.selectedItems.get(0).intValue();
                this.selectedItems.clear();
                notifyItemChanged(oldPosition);
            }
            this.selectedItems.add(Integer.valueOf(position));
            notifyItemChanged(position);
        }
    }

    public void changeSelections(int position) {
        if (!this.originalPositions.isEmpty()) {
            position = this.originalPositions.indexOf(Integer.valueOf(position));
        }
        if (this.selectedItems.contains(Integer.valueOf(position))) {
            this.selectedItems.remove(Integer.valueOf(position));
        } else {
            this.selectedItems.add(Integer.valueOf(position));
        }
        notifyItemChanged(position);
    }

    public void clearSelections() {
        this.selectedItems.clear();
    }

    class RvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public ImageView imageVieww;
        public TextView textViewFirst;
        public TextView textViewSecond;

        public RvViewHolder(View view, int idCard, int idFirst, int idSecond, int idImages) {
            super(view);
            view.setOnClickListener(this);
            this.cardView = view.findViewById(idCard);
            this.textViewFirst = (TextView) view.findViewById(idFirst);
            if (idSecond != -1) {
                this.textViewSecond = (TextView) view.findViewById(idSecond);
            }
            if (idImages != -1) {
                this.imageVieww = (ImageView) view.findViewById(idImages);
            }
        }

        public void onClick(View v) {
            int position = getAdapterPosition();
            if (!ListAdapterWithRecyclerView.this.originalPositions.isEmpty()) {
                position = ((Integer) ListAdapterWithRecyclerView.this.originalPositions.get(position)).intValue();
            }
            ListAdapterWithRecyclerView.this.clickListener.onItemClick(position, v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener2) {
        this.clickListener = clickListener2;
    }

    public Filter getFilter() {
        return this.filter;
    }
}
