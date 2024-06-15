package umc.spring.service.Review;

import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;

public class ReviewQueryServiceImpl implements ReviewQueryService{
    @Override
    public Review createReview(ReviewRequestDTO.CreateReviewDTO request, Member member, Store store){
        Review newReview = ReviewConverter.toReview(request, member, store);
        return null;
    }
}
