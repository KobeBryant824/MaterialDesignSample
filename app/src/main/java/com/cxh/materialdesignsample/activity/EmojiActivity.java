package com.cxh.materialdesignsample.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.text.emoji.EmojiCompat;
import android.widget.TextView;

import com.cxh.materialdesignsample.R;

import java.lang.ref.WeakReference;

/**
 * @author a-Hai (haigod7[at]gmail[dot]com)
 *         2017/10/17
 */
public class EmojiActivity extends BaseActivity {

    //Emoji 官网https://apps.timwhitlock.info/emoji/tables/unicode

    // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F4BB] (PERSONAL COMPUTER)
    // 中间那个是表情连接符，存在就会制作出另一个表情
    private static final String WOMAN_TECHNOLOGIST = "\uD83D\uDC69\u200D\uD83D\uDCBB";

    // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F3A4] (MICROPHONE)
    private static final String WOMAN_SINGER = "\uD83D\uDC69\u200D\uD83C\uDFA4";

    static final String EMOJI = WOMAN_TECHNOLOGIST + " " + WOMAN_SINGER ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle("Emoji");
        setContentView(R.layout.activity_emoji);
        super.onCreate(savedInstanceState);


        // TextView variant provided by EmojiCompat library
        final TextView emojiTextView = findViewById(R.id.emoji_text_view);
        emojiTextView.setText(getString(R.string.emoji_text_view, EMOJI));

        // EditText variant provided by EmojiCompat library
        final TextView emojiEditText = findViewById(R.id.emoji_edit_text);
        emojiEditText.setText(getString(R.string.emoji_edit_text, EMOJI));

        // Button variant provided by EmojiCompat library
        final TextView emojiButton = findViewById(R.id.emoji_button);
        emojiButton.setText(getString(R.string.emoji_button, EMOJI));

        // Regular TextView without EmojiCompat support; you have to manually process the text
        final TextView regularTextView = findViewById(R.id.regular_text_view);
        EmojiCompat.get().registerInitCallback(new InitCallback(regularTextView));

        // Custom TextView
        final TextView customTextView = findViewById(R.id.emoji_custom_text_view);
        customTextView.setText(getString(R.string.custom_text_view, EMOJI));
    }

    private static class InitCallback extends EmojiCompat.InitCallback {

        private final WeakReference<TextView> mRegularTextViewRef;

        InitCallback(TextView regularTextView) {
            mRegularTextViewRef = new WeakReference<>(regularTextView);
        }

        @Override
        public void onInitialized() {
            final TextView regularTextView = mRegularTextViewRef.get();
            if (regularTextView != null) {
                final EmojiCompat compat = EmojiCompat.get();
                final Context context = regularTextView.getContext();
                regularTextView.setText(
                        compat.process(context.getString(R.string.regular_text_view, EMOJI)));
            }
        }

    }
}
