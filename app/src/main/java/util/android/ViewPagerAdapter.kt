package util.android

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.example.charlie.githubbrowser.R


class ViewPagerAdapter(private val setUserView: (View) -> View,
                       private val setRepoView: (View) -> View): PagerAdapter() {

    override fun isViewFromObject(view: View?, obj: Any?): Boolean = view == obj

    override fun getCount(): Int = 2

    override fun instantiateItem(container: ViewGroup?, position: Int): Any = with(container!!) {
        val view =  when (position) {
            0 -> setUserView(inflate(R.layout.generic_recyclerview_layout))
            1 -> setRepoView(inflate(R.layout.generic_recyclerview_layout))
            else -> throw IllegalStateException()
        }
        addView(view)
        view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeViewAt(position)
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Users"
        1 -> "Repositories"
        else -> throw IllegalStateException()
    }
}