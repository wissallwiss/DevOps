import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import {
  LucideAngularModule,
  Clock, Users, ThumbsUp, ChevronLeft, Share2, Bookmark,
  MessageSquare, CheckCircle, Send, Play, Eye, Award, ShieldCheck, GraduationCap, ArrowRight
} from 'lucide-angular';
import { AcademyService } from '../services/academy.service';
import { Video } from '../models/academy.model';

@Component({
  selector: 'app-knowledge-video-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    LucideAngularModule
  ],
  templateUrl: './knowledge-video.component.html',
  styles: [`
    :host {
      display: block;
      background-color: #F1EDE1;
    }
    .video-hero-title {
      -webkit-text-stroke: 1px rgba(10, 31, 28, 0.05);
      color: transparent;
      line-height: 0.8;
    }
    .card-asymmetric {
      border-radius: 28px 32px 24px 30px;
    }
    .glass-card {
      background: rgba(10, 31, 28, 0.03);
      backdrop-filter: blur(12px);
      border: 1px solid rgba(10, 31, 28, 0.08);
    }
  `]
})
export class KnowledgeVideoComponent implements OnInit {
  // Icons
  readonly Clock = Clock;
  readonly Users = Users;
  readonly ThumbsUp = ThumbsUp;
  readonly ChevronLeft = ChevronLeft;
  readonly Share2 = Share2;
  readonly Bookmark = Bookmark;
  readonly MessageSquare = MessageSquare;
  readonly CheckCircle = CheckCircle;
  readonly Send = Send;
  readonly Play = Play;
  readonly Eye = Eye;
  readonly Award = Award;
  readonly ShieldCheck = ShieldCheck;
  readonly GraduationCap = GraduationCap;
  readonly ArrowRight = ArrowRight;

  videoId: string = '';
  video: Video | null = null;
  isLoading = true;
  newComment: string = '';
  isHelpful: boolean = false;
  isSaved: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private academyService: AcademyService
  ) { }

  ngOnInit() {
    console.log('KnowledgeVideoComponent initialized');
    this.route.params.subscribe(params => {
      console.log('Route params received:', params);
      this.videoId = params['videoId'];
      if (this.videoId) {
        console.log('Found videoId:', this.videoId);
        this.loadVideo(this.videoId);
      } else {
        console.warn('No videoId found in route params, component staying in loading state');
      }
    });

    // Safety recovery: if after 10 seconds still loading, force it
    setTimeout(() => {
      if (this.isLoading) {
        console.error('Video loading TIMEOUT at 10s! Forcing recovery...');
        this.isLoading = false;
        // If we still don't have a video, redirecting might be better or showing an error
      }
    }, 10000);
  }

  loadVideo(id: string) {
    this.isLoading = true;
    console.log('Calling academyService.getVideoById for:', id);
    this.academyService.getVideoById(id).subscribe({
      next: (video) => {
        console.log('Successfully retrieved video:', video.title);
        this.video = video;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('getVideoById failed!', err);
        console.log('Falling back to getVideos()...');
        // Fallback: load all videos and pick the first one
        this.academyService.getVideos().subscribe({
          next: (videos) => {
            console.log('Fallback success, videos found:', videos.length);
            this.video = videos.length > 0 ? videos[0] : null;
            this.isLoading = false;
          },
          error: (err2) => {
            console.error('Fallback failed!', err2);
            this.video = null;
            this.isLoading = false;
          }
        });
      }
    });
  }

  goBack() {
    this.router.navigate(['/academy']);
  }

  toggleHelpful() {
    this.isHelpful = !this.isHelpful;
    if (this.video) {
      this.video.helpfulCount += this.isHelpful ? 1 : -1;
    }
  }

  toggleSave() {
    this.isSaved = !this.isSaved;
  }

  postComment() {
    if (!this.newComment.trim() || !this.video) return;
    // In a real app, POST to backend. For now, add locally.
    this.video.comments.unshift({
      id: String(Date.now()),
      content: this.newComment.trim(),
      authorId: 'current-user',
      authorName: 'You',
      createdAt: new Date().toISOString()
    });
    this.newComment = '';
  }

  shareVideo() {
    if (this.video) {
      if (navigator.share) {
        navigator.share({
          title: this.video.title,
          text: `Watching "${this.video.title}" on Wilderness Academy!`,
          url: window.location.href
        }).catch(() => {
          navigator.clipboard.writeText(window.location.href);
        });
      } else {
        navigator.clipboard.writeText(window.location.href);
      }
    }
  }

  navigateToVideo(videoId: string) {
    this.router.navigate(['/academy/video', videoId]);
  }

  navigateToExpert(expertId: string) {
    this.router.navigate(['/academy/expert', expertId]);
  }
}
