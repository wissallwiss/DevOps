package com.campconnect.services;

import com.campconnect.dto.VideoDTO;
import com.campconnect.entity.Video;
import com.campconnect.repository.VideoRepository;
import com.campconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServicesImpl implements IVideoServices {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<VideoDTO> getAllVideos() {
        return videoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public VideoDTO getVideoById(String id) {
        return videoRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public VideoDTO createVideo(VideoDTO videoDTO) {
        Video video = convertToEntity(videoDTO);
        video.setCreatedAt(LocalDateTime.now());
        video.setViews(0);
        video.setHelpfulCount(0);
        return convertToDTO(videoRepository.save(video));
    }

    @Override
    public VideoDTO updateVideo(String id, VideoDTO videoDTO) {
        if (!videoRepository.existsById(id)) return null;
        Video video = convertToEntity(videoDTO);
        video.setId(id);
        return convertToDTO(videoRepository.save(video));
    }

    @Override
    public void deleteVideo(String id) {
        videoRepository.deleteById(id);
    }

    @Override
    public List<VideoDTO> getVideosByCategory(String category) {
        return videoRepository.findByCategory(category).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private VideoDTO convertToDTO(Video video) {
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setVideoUrl(video.getVideoUrl());
        dto.setThumbnailUrl(video.getThumbnailUrl());
        dto.setCategory(video.getCategory());
        dto.setType(video.getType());
        dto.setViews(video.getViews());
        dto.setHelpfulCount(video.getHelpfulCount());
        dto.setCreatedAt(video.getCreatedAt());
        dto.setTakeaways(video.getTakeaways());
        
        if (video.getCreator() != null) {
            com.campconnect.dto.UserSummaryDTO creatorDto = new com.campconnect.dto.UserSummaryDTO();
            creatorDto.setId(video.getCreator().getId());
            creatorDto.setUsername(video.getCreator().getUsername());
            creatorDto.setName(video.getCreator().getName());
            dto.setCreator(creatorDto);
        }
        
        return dto;
    }

    private Video convertToEntity(VideoDTO dto) {
        Video video = new Video();
        video.setTitle(dto.getTitle());
        video.setDescription(dto.getDescription());
        video.setVideoUrl(dto.getVideoUrl());
        video.setThumbnailUrl(dto.getThumbnailUrl());
        video.setCategory(dto.getCategory());
        video.setType(dto.getType());
        video.setTakeaways(dto.getTakeaways());
        
        if (dto.getCreator() != null) {
            String creatorId = dto.getCreator().getId();
            String username = dto.getCreator().getUsername();
            
            if (creatorId != null) {
                userRepository.findById(creatorId).ifPresent(video::setCreator);
            }
            
            // Fallback: if creator still not set, try finding by username (especially for 'admin')
            if (video.getCreator() == null && (username != null || "admin".equals(creatorId))) {
                String searchName = username != null ? username : "admin";
                userRepository.findByUsername(searchName).ifPresent(video::setCreator);
            }
        }
        
        return video;
    }
}
