package com.snavi.androidtv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BrowseSupportFragment {

    // CONST //////////////////////////////////////////////////////////////////////////////////////
    private static final String MAIN_CATEGORY_NAME = "Images";



    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        setupUIElements();
        loadRows();
        setupEventListeners();
    }



    private void loadRows()
    {
        List<Img> imgList = getImages();

        ArrayObjectAdapter rowsAdapter    = new ArrayObjectAdapter(new ListRowPresenter());         // all rows (title on left bar + content)
        CardPresenter cardPresenter       = new CardPresenter();                                    // holds image and it's title
        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);                  // only content (images with title in this case)

        for (int i = 0; i < imgList.size(); ++i)                                                    // add images to display (only one category)
            listRowAdapter.add(imgList.get(i));

        HeaderItem header = new HeaderItem(0, MAIN_CATEGORY_NAME);                              // title of category (on left bar)
        rowsAdapter.add(new ListRow(header, listRowAdapter));                                       // add content

        setAdapter(rowsAdapter);
    }



    private void setupUIElements()
    {
        setTitle(getString(R.string.browse_title));
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        if (getActivity() == null) return;
        setBrandColor(ContextCompat.getColor(getActivity(), R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(ContextCompat.getColor(getActivity(), R.color.search_opaque));
    }



    private void setupEventListeners()
    {
        setOnSearchClickedListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Sorry, this option is not available", Toast.LENGTH_LONG)
                        .show();
            }
        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
    }



    private List<Img> getImages()
    {
        List<Img> imgList = new ArrayList<>(3);
        if (getContext() == null) return imgList;

        imgList.add(new Img(getContext().getDrawable(R.drawable.m_1), R.drawable.m_1, "mountain 1"));
        imgList.add(new Img(getContext().getDrawable(R.drawable.m_2), R.drawable.m_2, "mountain 2"));
        imgList.add(new Img(getContext().getDrawable(R.drawable.m_3), R.drawable.m_3, "mountain 3"));

        return imgList;
    }



    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row)
        {
            Intent intent = new Intent(getActivity(), FullscreenImageActivity.class);
            int imgId = ((Img) item).getImageId();
            intent.putExtra(FullscreenImageActivity.PHOTO_ID_KEY, imgId);
            startActivity(intent);
        }
    }

}
