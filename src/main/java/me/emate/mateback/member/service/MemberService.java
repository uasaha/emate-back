package me.emate.mateback.member.service;

import me.emate.mateback.member.dto.*;

/**
 * Member service입니다.
 *
 * @author 여운석
 */
public interface MemberService {

    /**
     * Member login member nickname response dto.
     *
     * @param requestDto the request dto
     * @return the member nickname response dto
     */
    MemberNicknameResponseDto memberLogin(LoginMemberRequestDto requestDto);

    /**
     * Is id conflict boolean.
     *
     * @param requestDto the request dto
     * @return the boolean
     */
    boolean isIdConflict(CheckIDRequestDto requestDto);

    /**
     * Is nick conflict boolean.
     *
     * @param requestDto the request dto
     * @return the boolean
     */
    boolean isNickConflict(CheckNicknameRequestDto requestDto);

    /**
     * Is email conflict boolean.
     *
     * @param requestDto the request dto
     * @return the boolean
     */
    boolean isEmailConflict(CheckEmailRequestDto requestDto);

    /**
     * Signup.
     *
     * @param requestDto the request dto
     */
    void signup(RegisterMemberRequestDto requestDto);

    /**
     * Gets member details.
     *
     * @param memberNo the member no
     * @return the member details
     */
    MemberDetailResponseDto getMemberDetails(Integer memberNo);
}
