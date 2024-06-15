package umc.spring.service.Review;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    @Transactional
    Review createReview(ReviewRequestDTO.CreateReviewDTO request);
}
