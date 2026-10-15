package net.yupol.transmissionremote.app.filtering;

import androidx.annotation.NonNull;

import net.yupol.transmissionremote.app.R;
import net.yupol.transmissionremote.app.model.json.Torrent;

import java.util.Locale;

public class NameFilter extends BaseFilter {

    private String query;

    public NameFilter() {
        super(R.string.filter_name, R.string.filter_empty_name);
    }

    public NameFilter withQuery(@NonNull String query) {
        this.query = query.toLowerCase(Locale.ROOT);
        return this;
    }

    @Override
    public boolean test(Torrent torrent) {
        return torrent.getName().toLowerCase(Locale.ROOT).contains(query);
    }
}
