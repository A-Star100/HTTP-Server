package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.LayoutType;
import com.google.appinventor.components.common.ListOrientation;
import com.google.appinventor.components.runtime.ListAdapterWithRecyclerView;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>This is a visible component that displays a list of text and image elements.</p> <p>Simple lists of strings may be set using the ElementsFromString property. More complex lists of elements containing multiple strings and/or images can be created using the ListData and ListViewLayout properties. </p>", iconName = "images/listView.png", nonVisible = false, version = 8)
@UsesLibraries({"recyclerview.jar", "recyclerview.aar", "cardview.jar", "cardview.aar", "dynamicanimation.jar"})
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.READ_EXTERNAL_STORAGE")
public final class ListView extends AndroidViewComponent {
    private static final int DEFAULT_BACKGROUND_COLOR = -16777216;
    private static final int DEFAULT_DIVIDER_SIZE = 0;
    private static final boolean DEFAULT_ENABLED = false;
    private static final int DEFAULT_IMAGE_WIDTH = 200;
    private static final int DEFAULT_MARGINS_SIZE = 0;
    private static final int DEFAULT_RADIUS = 0;
    private static final int DEFAULT_TEXT_SIZE = 22;
    private static final String LOG_TAG = "ListView";
    private int backgroundColor;
    private ListBounceEdgeEffectFactory bounceEdgeEffectFactory;
    private boolean bounceEffect;
    protected final ComponentContainer container;
    private int detailTextColor;
    private boolean divider;
    private int dividerColor;
    /* access modifiers changed from: private */
    public Paint dividerPaint;
    /* access modifiers changed from: private */
    public int dividerSize;
    private RecyclerView.EdgeEffectFactory edgeEffectFactory;
    private int elementColor;
    /* access modifiers changed from: private */
    public boolean first = true;
    private float fontSizeDetail;
    private float fontSizeMain;
    private String fontTypeDetail;
    private String fontTypeface;
    private String hint;
    private int imageHeight;
    private int imageWidth;
    private List<Object> items;
    private int layout;
    private LinearLayoutManager layoutManager;
    private final LinearLayout linearLayout;
    /* access modifiers changed from: private */
    public ListAdapterWithRecyclerView listAdapterWithRecyclerView;
    private final LinearLayout listLayout;
    /* access modifiers changed from: private */
    public int margins;
    private boolean multiSelect;
    /* access modifiers changed from: private */
    public int orientation;
    private String propertyValue;
    private int radius;
    private RecyclerView recyclerView;
    private String selection;
    private int selectionColor;
    private String selectionDetailText = "Uninitialized";
    private int selectionIndex;
    private boolean showFilter = false;
    private int textColor;
    private EditText txtSearchBox;

    public ListView(ComponentContainer container2) {
        super(container2);
        this.container = container2;
        this.items = new ArrayList();
        LinearLayout linearLayout2 = new LinearLayout(container2.$context());
        this.linearLayout = linearLayout2;
        linearLayout2.setOrientation(1);
        this.orientation = 0;
        this.layout = 0;
        this.recyclerView = new RecyclerView(container2.$context());
        this.recyclerView.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container2.$context(), 1, false);
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        LinearLayout linearLayout3 = new LinearLayout(container2.$context());
        this.listLayout = linearLayout3;
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout3.setOrientation(1);
        this.dividerColor = -1;
        this.dividerSize = 0;
        this.margins = 0;
        this.edgeEffectFactory = this.recyclerView.getEdgeEffectFactory();
        this.bounceEdgeEffectFactory = new ListBounceEdgeEffectFactory();
        EditText editText = new EditText(container2.$context());
        this.txtSearchBox = editText;
        editText.setSingleLine(true);
        this.txtSearchBox.setWidth(-2);
        this.txtSearchBox.setPadding(10, 10, 10, 10);
        HintText("Search list...");
        if (!AppInventorCompatActivity.isClassicMode()) {
            this.txtSearchBox.setBackgroundColor(-1);
        }
        if (container2.$form().isDarkTheme()) {
            this.txtSearchBox.setTextColor(-16777216);
            this.txtSearchBox.setHintTextColor(-16777216);
        }
        this.txtSearchBox.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                ListView.this.listAdapterWithRecyclerView.getFilter().filter(cs);
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable arg0) {
            }
        });
        if (this.showFilter) {
            this.txtSearchBox.setVisibility(0);
        } else {
            this.txtSearchBox.setVisibility(8);
        }
        ElementColor(-16777216);
        BackgroundColor(-16777216);
        SelectionColor(Component.COLOR_LTGRAY);
        TextColor(-1);
        TextColorDetail(-1);
        DividerColor(-1);
        DividerThickness(0);
        ElementMarginsWidth(0);
        FontSize(22.0f);
        FontSizeDetail(14.0f);
        FontTypeface(Component.TYPEFACE_DEFAULT);
        FontTypefaceDetail(Component.TYPEFACE_DEFAULT);
        ImageWidth(200);
        ImageHeight(200);
        ElementCornerRadius(0);
        MultiSelect(false);
        BounceEdgeEffect(false);
        ElementsFromString("");
        ListData("");
        linearLayout3.addView(this.recyclerView);
        linearLayout2.addView(this.txtSearchBox);
        linearLayout2.addView(linearLayout3);
        linearLayout2.requestLayout();
        container2.$add(this);
        Width(-2);
        ListViewLayout(0);
        SelectionIndex(0);
        setDivider();
    }

    public View getView() {
        return this.linearLayout;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the height of the list on the view.")
    public void Height(int height) {
        if (height == -1) {
            height = -2;
        }
        super.Height(height);
    }

    @DesignerProperty(defaultValue = "Search list...", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets hint on the filter bar.")
    public void HintText(String hint2) {
        this.hint = hint2;
        this.txtSearchBox.setHint(hint2);
    }

    @SimpleProperty
    public String HintText() {
        return this.hint;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the width of the list on the view.")
    public void Width(int width) {
        if (width == -1) {
            width = -2;
        }
        super.Width(width);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowFilterBar(boolean showFilter2) {
        this.showFilter = showFilter2;
        if (showFilter2) {
            this.txtSearchBox.setVisibility(0);
        } else {
            this.txtSearchBox.setVisibility(8);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "List filter bar, allows to search the list for relevant items. True will display the bar, False will hide it.")
    public boolean ShowFilterBar() {
        return this.showFilter;
    }

    @SimpleProperty
    public void Elements(List<Object> itemsList) {
        this.items = new ArrayList(itemsList);
        updateAdapterData();
        this.listAdapterWithRecyclerView.notifyDataSetChanged();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "List of elements to show in the ListView. Depending on the ListView, this may be a list of strings or a list of 3-element sub-lists containing Text, Description, and Image file name.")
    public List<Object> Elements() {
        return this.items;
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The TextView elements specified as a string with the stringItems separated by commas such as: Cheese,Fruit,Bacon,Radish. Each word before the comma will be an element in the list.")
    public void ElementsFromString(String itemstring) {
        this.items = new ArrayList(ElementsUtil.elementsListFromString(itemstring));
        updateAdapterData();
        this.listAdapterWithRecyclerView.notifyDataSetChanged();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The index of the currently selected item, starting at 1. If no item is selected, the value will be 0. If an attempt is made to set this to a number less than 1 or greater than the number of stringItems in the ListView, SelectionIndex will be set to 0, and Selection will be set to the empty text.")
    public int SelectionIndex() {
        return this.selectionIndex;
    }

    @SimpleProperty
    public void SelectionIndex(int index) {
        this.selectionIndex = index;
        if (index <= 0 || index > this.items.size()) {
            this.selection = "";
            this.listAdapterWithRecyclerView.clearSelections();
            this.listAdapterWithRecyclerView.notifyDataSetChanged();
            return;
        }
        Object o = this.items.get(index - 1);
        if (!(o instanceof YailDictionary)) {
            this.selection = o.toString();
        } else if (((YailDictionary) o).containsKey(Component.LISTVIEW_KEY_MAIN_TEXT)) {
            this.selection = ((YailDictionary) o).get(Component.LISTVIEW_KEY_MAIN_TEXT).toString();
            this.selectionDetailText = ElementsUtil.toStringEmptyIfNull(((YailDictionary) o).get(Component.LISTVIEW_KEY_DESCRIPTION));
        } else {
            this.selection = o.toString();
        }
        if (this.multiSelect) {
            this.listAdapterWithRecyclerView.changeSelections(this.selectionIndex - 1);
        } else {
            this.listAdapterWithRecyclerView.toggleSelection(this.selectionIndex - 1);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The text value of the most recently selected item in the ListView.")
    public String Selection() {
        return this.selection;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void Selection(String value) {
        this.selection = value;
        if (!this.items.isEmpty()) {
            int i = 0;
            while (true) {
                if (i >= this.items.size()) {
                    break;
                }
                Object item = this.items.get(i);
                if (item instanceof YailDictionary) {
                    if (((YailDictionary) item).containsKey(Component.LISTVIEW_KEY_MAIN_TEXT)) {
                        if (((YailDictionary) item).get(Component.LISTVIEW_KEY_MAIN_TEXT).toString() == value) {
                            this.selectionIndex = i + 1;
                            this.selectionDetailText = ElementsUtil.toStringEmptyIfNull(((YailDictionary) item).get(Component.LISTVIEW_KEY_DESCRIPTION));
                            break;
                        }
                        this.selectionIndex = 0;
                        this.selectionDetailText = "Not Found";
                    } else if (item.toString().equals(value)) {
                        this.selectionIndex = i + 1;
                        break;
                    } else {
                        this.selectionIndex = 0;
                    }
                } else if (item.toString().equals(value)) {
                    this.selectionIndex = i + 1;
                    break;
                } else {
                    this.selectionIndex = 0;
                }
                i++;
            }
            SelectionIndex(this.selectionIndex);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the secondary text of the selected row in the ListView.")
    public String SelectionDetailText() {
        return this.selectionDetailText;
    }

    @SimpleEvent(description = "Simple event to be raised after the an element has been chosen in the list. The selected element is available in the Selection property.")
    public void AfterPicking() {
        EventDispatcher.dispatchEvent(this, "AfterPicking", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the listview background.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public void BackgroundColor(int argb) {
        this.backgroundColor = argb;
        this.recyclerView.setBackgroundColor(argb);
        this.linearLayout.setBackgroundColor(this.backgroundColor);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the listview background.")
    public int ElementColor() {
        return this.elementColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public void ElementColor(int argb) {
        this.elementColor = argb;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the item when it is selected.")
    public int SelectionColor() {
        return this.selectionColor;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty
    public void SelectionColor(int argb) {
        this.selectionColor = argb;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text color of the listview stringItems.")
    public int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void TextColor(int argb) {
        this.textColor = argb;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text color of DetailText of listview stringItems. ")
    public int TextColorDetail() {
        return this.detailTextColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void TextColorDetail(int argb) {
        this.detailTextColor = argb;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text size of the listview items.")
    public int TextSize() {
        return Math.round(this.fontSizeMain);
    }

    @DesignerProperty(defaultValue = "22", editorType = "non_negative_integer")
    @SimpleProperty
    public void TextSize(int textSize) {
        if (textSize > 1000) {
            textSize = 999;
        }
        FontSize(Float.valueOf((float) textSize).floatValue());
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text size of the listview stringItems.", userVisible = false)
    public float FontSize() {
        return this.fontSizeMain;
    }

    @SimpleProperty
    public void FontSize(float fontSize) {
        if (fontSize > 1000.0f || fontSize < 1.0f) {
            this.fontSizeMain = 999.0f;
        } else {
            this.fontSizeMain = fontSize;
        }
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The text size of the listview stringItems.")
    public float FontSizeDetail() {
        return this.fontSizeDetail;
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty
    public void FontSizeDetail(float fontSize) {
        if (fontSize > 1000.0f || fontSize < 1.0f) {
            this.fontSizeDetail = 999.0f;
        } else {
            this.fontSizeDetail = fontSize;
        }
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public String FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void FontTypeface(String typeface) {
        this.fontTypeface = typeface;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public String FontTypefaceDetail() {
        return this.fontTypeDetail;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void FontTypefaceDetail(String typeface) {
        this.fontTypeDetail = typeface;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The image width of the listview image.")
    public int ImageWidth() {
        return this.imageWidth;
    }

    @DesignerProperty(defaultValue = "200", editorType = "non_negative_integer")
    @SimpleProperty
    public void ImageWidth(int width) {
        this.imageWidth = width;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The image height of the listview image stringItems.")
    public int ImageHeight() {
        return this.imageHeight;
    }

    @DesignerProperty(defaultValue = "200", editorType = "non_negative_integer")
    @SimpleProperty
    public void ImageHeight(int height) {
        this.imageHeight = height;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Selecting the text and image layout in the ListView element.")
    public int ListViewLayout() {
        return this.layout;
    }

    @DesignerProperty(defaultValue = "0", editorType = "ListViewLayout")
    @SimpleProperty
    public void ListViewLayout(@Options(LayoutType.class) int value) {
        this.layout = value;
        setAdapterData();
    }

    public boolean MultiSelect() {
        return this.multiSelect;
    }

    public void MultiSelect(boolean multi) {
        if (this.selectionIndex > 0) {
            this.listAdapterWithRecyclerView.clearSelections();
            this.listAdapterWithRecyclerView.notifyDataSetChanged();
        }
        this.multiSelect = multi;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the layout's orientation (vertical, horizontal).")
    public int Orientation() {
        return this.orientation;
    }

    @DesignerProperty(defaultValue = "0", editorType = "recyclerview_orientation")
    @SimpleProperty
    public void Orientation(@Options(ListOrientation.class) int orientation2) {
        this.orientation = orientation2;
        if (orientation2 == 1) {
            this.layoutManager.setOrientation(0);
        } else {
            this.layoutManager.setOrientation(1);
        }
        this.recyclerView.requestLayout();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String ListData() {
        return this.propertyValue;
    }

    @DesignerProperty(editorType = "ListViewAddData")
    @SimpleProperty(userVisible = false)
    public void ListData(String propertyValue2) {
        this.propertyValue = propertyValue2;
        if (propertyValue2 != null && !propertyValue2.isEmpty() && !propertyValue2.equals("[]")) {
            try {
                JSONArray arr = new JSONArray(propertyValue2);
                this.items.clear();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonItem = arr.getJSONObject(i);
                    YailDictionary yailItem = new YailDictionary();
                    if (jsonItem.has(Component.LISTVIEW_KEY_MAIN_TEXT)) {
                        yailItem.put(Component.LISTVIEW_KEY_MAIN_TEXT, jsonItem.getString(Component.LISTVIEW_KEY_MAIN_TEXT));
                        String str = "";
                        yailItem.put(Component.LISTVIEW_KEY_DESCRIPTION, jsonItem.has(Component.LISTVIEW_KEY_DESCRIPTION) ? jsonItem.getString(Component.LISTVIEW_KEY_DESCRIPTION) : str);
                        if (jsonItem.has(Component.LISTVIEW_KEY_IMAGE)) {
                            str = jsonItem.getString(Component.LISTVIEW_KEY_IMAGE);
                        }
                        yailItem.put(Component.LISTVIEW_KEY_IMAGE, str);
                        this.items.add(yailItem);
                    }
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Malformed JSON in ListView.ListData", e);
                this.container.$form().dispatchErrorOccurredEvent(this, "ListView.ListData", 0, e.getMessage());
            }
            updateAdapterData();
            this.listAdapterWithRecyclerView.notifyDataSetChanged();
        }
    }

    @SimpleFunction(description = "Create a ListView entry. MainText is required. DetailText and ImageName are optional.")
    public YailDictionary CreateElement(String mainText, String detailText, String imageName) {
        YailDictionary dictItem = new YailDictionary();
        dictItem.put(Component.LISTVIEW_KEY_MAIN_TEXT, mainText);
        dictItem.put(Component.LISTVIEW_KEY_DESCRIPTION, detailText);
        dictItem.put(Component.LISTVIEW_KEY_IMAGE, imageName);
        return dictItem;
    }

    @SimpleFunction(description = "Get the Main Text of a ListView element.")
    public String GetMainText(YailDictionary listElement) {
        return listElement.get(Component.LISTVIEW_KEY_MAIN_TEXT).toString();
    }

    @SimpleFunction(description = "Get the Detail Text of a ListView element.")
    public String GetDetailText(YailDictionary listElement) {
        return listElement.get(Component.LISTVIEW_KEY_DESCRIPTION).toString();
    }

    @SimpleFunction(description = "Get the filename of the image of a ListView element that has been uploaded to Media.")
    public String GetImageName(YailDictionary listElement) {
        return listElement.get(Component.LISTVIEW_KEY_IMAGE).toString();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the list view divider.")
    public int DividerColor() {
        return this.dividerColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void DividerColor(int argb) {
        this.dividerColor = argb;
        Paint paint = new Paint();
        this.dividerPaint = paint;
        paint.setColor(argb);
        setDivider();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The thickness of the element divider in the list view. If the thickness is 0, the divider is not visible.")
    public int DividerThickness() {
        return this.dividerSize;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty
    public void DividerThickness(int size) {
        this.dividerSize = size;
        setDivider();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The margins width of the list view element.")
    public int ElementMarginsWidth() {
        return this.margins;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty
    public void ElementMarginsWidth(int width) {
        this.margins = width;
        setDivider();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The radius of the rounded corners of a list view item.")
    public int ElementCornerRadius() {
        return this.radius;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty
    public void ElementCornerRadius(int radius2) {
        this.radius = radius2;
        setAdapterData();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The effect of bounce from the edge after scrolling the list to the end.  True will enable the bounce effect, false will disable it.")
    public boolean BounceEdgeEffect() {
        return this.bounceEffect;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void BounceEdgeEffect(boolean bounce) {
        if (bounce) {
            this.recyclerView.setEdgeEffectFactory(this.bounceEdgeEffectFactory);
        } else {
            this.recyclerView.setEdgeEffectFactory(this.edgeEffectFactory);
        }
        this.bounceEffect = bounce;
    }

    @SimpleFunction(description = "Removes Item from list at a given index.")
    public void RemoveItemAtIndex(int index) {
        if (index < 1 || index > this.items.size()) {
            this.container.$form().dispatchErrorOccurredEvent(this, "RemoveItemAtIndex", ErrorMessages.ERROR_LISTVIEW_INDEX_OUT_OF_BOUNDS, Integer.valueOf(index));
            return;
        }
        this.items.remove(index - 1);
        updateAdapterData();
        this.listAdapterWithRecyclerView.notifyItemRemoved(index - 1);
    }

    @SimpleFunction(description = "Add new Item to list at the end.")
    public void AddItem(String mainText, String detailText, String imageName) {
        if (!this.items.isEmpty()) {
            Object o = this.items.get(0);
            if (!(o instanceof YailDictionary)) {
                this.items.add(mainText);
            } else if (((YailDictionary) o).containsKey(Component.LISTVIEW_KEY_MAIN_TEXT)) {
                this.items.add(CreateElement(mainText, detailText, imageName));
            } else {
                this.items.add(mainText);
            }
        } else if (this.layout == 0) {
            this.items.add(mainText);
        } else {
            this.items.add(CreateElement(mainText, detailText, imageName));
        }
        updateAdapterData();
        ListAdapterWithRecyclerView listAdapterWithRecyclerView2 = this.listAdapterWithRecyclerView;
        listAdapterWithRecyclerView2.notifyItemChanged(listAdapterWithRecyclerView2.getItemCount() - 1);
    }

    @SimpleFunction(description = "Add new Items to list at the end.")
    public void AddItems(List<Object> itemsList) {
        if (!itemsList.isEmpty()) {
            int positionStart = this.items.size();
            int itemCount = itemsList.size();
            this.items.addAll(itemsList);
            updateAdapterData();
            this.listAdapterWithRecyclerView.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @SimpleFunction(description = "Add new Item to list at a given index.")
    public void AddItemAtIndex(int index, String mainText, String detailText, String imageName) {
        if (index < 1 || index > this.items.size() + 1) {
            this.container.$form().dispatchErrorOccurredEvent(this, "AddItemAtIndex", ErrorMessages.ERROR_LISTVIEW_INDEX_OUT_OF_BOUNDS, Integer.valueOf(index));
            return;
        }
        if (!this.items.isEmpty()) {
            Object o = this.items.get(0);
            if (!(o instanceof YailDictionary)) {
                this.items.add(index - 1, mainText);
            } else if (((YailDictionary) o).containsKey(Component.LISTVIEW_KEY_MAIN_TEXT)) {
                this.items.add(index - 1, CreateElement(mainText, detailText, imageName));
            } else {
                this.items.add(index - 1, mainText);
            }
        } else if (this.layout == 0) {
            this.items.add(index - 1, mainText);
        } else {
            this.items.add(index - 1, CreateElement(mainText, detailText, imageName));
        }
        updateAdapterData();
        this.listAdapterWithRecyclerView.notifyItemInserted(index - 1);
    }

    @SimpleFunction(description = "Add new Items to list at specific index.")
    public void AddItemsAtIndex(int index, YailList itemsList) {
        if (index < 1 || index > this.items.size() + 1) {
            this.container.$form().dispatchErrorOccurredEvent(this, "AddItemsAtIndex", ErrorMessages.ERROR_LISTVIEW_INDEX_OUT_OF_BOUNDS, Integer.valueOf(index));
        } else if (!itemsList.isEmpty()) {
            int positionStart = index - 1;
            int itemCount = itemsList.size();
            this.items.addAll(positionStart, itemsList);
            updateAdapterData();
            this.listAdapterWithRecyclerView.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    public void setAdapterData() {
        ListAdapterWithRecyclerView listAdapterWithRecyclerView2 = r1;
        ListAdapterWithRecyclerView listAdapterWithRecyclerView3 = new ListAdapterWithRecyclerView(this.container, this.items, this.layout, this.textColor, this.detailTextColor, this.fontSizeMain, this.fontSizeDetail, this.fontTypeface, this.fontTypeDetail, this.elementColor, this.selectionColor, this.imageWidth, this.imageHeight, this.radius);
        ListAdapterWithRecyclerView listAdapterWithRecyclerView4 = listAdapterWithRecyclerView2;
        this.listAdapterWithRecyclerView = listAdapterWithRecyclerView4;
        listAdapterWithRecyclerView4.setOnItemClickListener(new ListAdapterWithRecyclerView.ClickListener() {
            public void onItemClick(int position, View v) {
                ListView.this.SelectionIndex(position + 1);
                ListView.this.AfterPicking();
            }
        });
        this.recyclerView.setAdapter(this.listAdapterWithRecyclerView);
    }

    public void updateAdapterData() {
        SelectionIndex(0);
        this.listAdapterWithRecyclerView.updateData(this.items);
    }

    private void setDivider() {
        DividerItemDecoration dividerDecoration = new DividerItemDecoration();
        int i = 0;
        while (true) {
            if (i >= this.recyclerView.getItemDecorationCount()) {
                break;
            } else if (this.recyclerView.getItemDecorationAt(i) instanceof DividerItemDecoration) {
                this.recyclerView.removeItemDecorationAt(i);
                break;
            } else {
                i++;
            }
        }
        this.recyclerView.addItemDecoration(dividerDecoration);
    }

    private class DividerItemDecoration extends RecyclerView.ItemDecoration {
        public DividerItemDecoration() {
        }

        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            RecyclerView recyclerView = parent;
            if (ListView.this.margins == 0) {
                int childCount = parent.getChildCount();
                if (ListView.this.orientation == 1) {
                    for (int i = 0; i < childCount - 1; i++) {
                        View child = recyclerView.getChildAt(i);
                        if (recyclerView.getChildAdapterPosition(child) != -1) {
                            int left = child.getRight();
                            int top = child.getTop();
                            Canvas canvas2 = canvas;
                            canvas2.drawRect((float) left, (float) top, (float) (ListView.this.dividerSize + left), (float) child.getBottom(), ListView.this.dividerPaint);
                        }
                    }
                    return;
                }
                int width = parent.getWidth();
                for (int i2 = 0; i2 < childCount - 1; i2++) {
                    View child2 = recyclerView.getChildAt(i2);
                    if (recyclerView.getChildAdapterPosition(child2) != -1) {
                        int top2 = child2.getBottom();
                        Canvas canvas3 = canvas;
                        canvas3.drawRect(0.0f, (float) top2, (float) width, (float) (ListView.this.dividerSize + top2), ListView.this.dividerPaint);
                    }
                }
            }
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            if (ListView.this.margins != 0) {
                int column = position % 1;
                outRect.left = ListView.this.margins - ((ListView.this.margins * column) / 1);
                outRect.right = ((column + 1) * ListView.this.margins) / 1;
                if (position < 1 || ListView.this.first) {
                    ListView.this.first = false;
                    outRect.top = ListView.this.margins;
                }
                outRect.bottom = ListView.this.margins;
            } else if (position == -1 || position >= parent.getAdapter().getItemCount() - 1) {
                outRect.setEmpty();
            } else if (ListView.this.orientation == 1) {
                outRect.set(0, 0, ListView.this.dividerSize, 0);
            } else {
                outRect.set(0, 0, 0, ListView.this.dividerSize);
            }
        }
    }

    @Deprecated
    @SimpleFunction(description = "Reload the ListView to reflect any changes in the data.")
    public void Refresh() {
        setAdapterData();
    }
}
