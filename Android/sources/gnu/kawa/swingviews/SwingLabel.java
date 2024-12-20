package gnu.kawa.swingviews;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import javax.swing.JLabel;

/* compiled from: SwingDisplay */
class SwingLabel extends JLabel implements ModelListener {
    Label model;

    public SwingLabel(Label model2) {
        this.model = model2;
        String text = model2.getText();
        if (text != null) {
            SwingLabel.super.setText(text);
        }
        model2.addListener((ModelListener) this);
    }

    public void modelUpdated(Model model2, Object key) {
        Label label;
        if (key == PropertyTypeConstants.PROPERTY_TYPE_TEXT && model2 == (label = this.model)) {
            SwingLabel.super.setText(label.getText());
        }
    }

    public void setText(String text) {
        Label label = this.model;
        if (label == null) {
            SwingLabel.super.setText(text);
        } else {
            label.setText(text);
        }
    }
}
