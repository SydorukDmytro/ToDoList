package sydoruk.services.interfaces;

import sydoruk.domain.Tag;

public interface ITagService {
    Tag findOrCreate(Tag tag);
}
