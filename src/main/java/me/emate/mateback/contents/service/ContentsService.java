package me.emate.mateback.contents.service;

import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;

public interface ContentsService {
    ContentsDetailResponseDto createContents(CreateContentsRequestDto requestDto);
}
