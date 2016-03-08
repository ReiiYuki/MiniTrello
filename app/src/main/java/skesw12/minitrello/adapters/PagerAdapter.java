package skesw12.minitrello.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import skesw12.minitrello.activities.CardFragments;
import skesw12.minitrello.models.CardList;
import skesw12.minitrello.models.Storage;

/**
 * Created by nattapat on 3/6/2016 AD.
 */
public class PagerAdapter  extends FragmentStatePagerAdapter {
    List<CardList> list;
    private List<Fragment> fragments;
    private FragmentManager fm;

    public PagerAdapter(FragmentManager fm , List list) {
        super(fm);
        this.fm = fm;
        this.list = list;
        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return CardFragments.newInstance(list.get(position).getTitle(),
                Storage.getInstance().loadCardLists().get(position));
    }

//    public Fragment getActiveFragment(ViewPager container, int position) {
//        String name = "android:switcher:" + container.getId() + ":" + position;
//        return fm.findFragmentByTag(name);
//    }

    @Override
    public int getCount() {
        return list.size();
    }
}

