package com.snavi.androidtv;

import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;

/*
 * A CardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains an Image CardView
 */
public class CardPresenter extends Presenter {


    // CONST //////////////////////////////////////////////////////////////////////////////////////
    private static final int CARD_WIDTH = 313;
    private static final int CARD_HEIGHT = 176;


    // fields /////////////////////////////////////////////////////////////////////////////////////
    private static int m_selectedBackgroundColor;
    private static int m_defaultBackgroundColor;



    private static void updateCardBackgroundColor(ImageCardView view, boolean selected) {
        int color = selected ? m_selectedBackgroundColor : m_defaultBackgroundColor;
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.info_field).setBackgroundColor(color);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent)
    {
        m_defaultBackgroundColor =
                ContextCompat.getColor(parent.getContext(), R.color.default_background);
        m_selectedBackgroundColor =
                ContextCompat.getColor(parent.getContext(), R.color.selected_background);

        ImageCardView cardView = createCardView(parent);
        setUpCardView(cardView);

        return new ViewHolder(cardView);
    }



    private ImageCardView createCardView(ViewGroup parent)
    {
        return new ImageCardView(parent.getContext()) {
            @Override
            public void setSelected(boolean selected) {
                updateCardBackgroundColor(this, selected);
                super.setSelected(selected);
            }
        };
    }



    private void setUpCardView(ImageCardView cardView)
    {
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        updateCardBackgroundColor(cardView, false);
    }



    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item)
    {
        Img img = (Img) item;
        ImageCardView cardView = (ImageCardView) viewHolder.view;

        cardView.setTitleText(img.getTitle());
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        cardView.setMainImage(img.getImage());
    }



    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder)
    {
        ImageCardView cardView = (ImageCardView) viewHolder.view;
        // Remove references to images so that the garbage collector can free up memory
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }
}
