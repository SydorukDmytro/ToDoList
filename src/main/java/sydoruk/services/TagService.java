package sydoruk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sydoruk.domain.Tag;
import sydoruk.repositories.TagRepository;
import sydoruk.services.interfaces.ITagService;

import java.util.List;

@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Tag findOrCreate(Tag tag) {
        List<Tag> tagList = tagRepository.findByName(tag.getName());
        if(tagList.isEmpty()){
            tagRepository.save(tag);
            return tag;
        }else{
            return tagList.get(0);
        }

    }
}
