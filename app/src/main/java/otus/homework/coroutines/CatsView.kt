package otus.homework.coroutines

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import androidx.constraintlayout.widget.ConstraintLayout

class CatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ICatsView {

    var presenter :CatsPresenter? = null

    override fun populate(fact: Fact, image: CatImage) {
        findViewById<TextView>(R.id.fact_textView).text = fact.fact
        Picasso.get().load(image.url).into(findViewById<ImageView>(R.id.cat_imageView));
}
     override fun onError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

interface ICatsView {

    fun populate(fact: Fact, image: CatImage)

    fun onError(text: String)
}
