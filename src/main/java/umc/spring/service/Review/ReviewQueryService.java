package umc.spring.service.Review;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewQueryService {
    Review createReview(ReviewRequestDTO.CreateReviewDTO request, Member member, Store store);
}
