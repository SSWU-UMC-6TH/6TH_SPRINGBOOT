package umc.spring.service.Review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewRequestDTO.CreateReviewDTO request, Member member, Store store){
        Review newReview = ReviewConverter.toReview(request, member, store);
        return null;
    }

    @Override
    public Page<Review> getMyReviewList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId).get();

        Page<Review> myPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return myPage;
    }
}
