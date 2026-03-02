import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { LucideAngularModule, ChevronLeft, Clock, BookOpen, Star, CheckCircle, Award, Users, ShieldCheck, Medal, Play, ChevronRight, TrendingUp, Target, Briefcase, FileText, GraduationCap, ArrowRight } from 'lucide-angular';

@Component({
  selector: 'app-certification-programs-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    LucideAngularModule
  ],
  templateUrl: './certification-programs.component.html',
  styles: [`
    :host { 
      display: block; 
      background-color: #F1EDE1;
    }
    .hero-outline-text {
      -webkit-text-stroke: 1px rgba(10, 31, 28, 0.05);
      color: transparent;
      line-Height: 0.8;
    }
    .card-asymmetric {
      border-radius: 28px 32px 24px 30px;
    }
    .medallion-card {
      border-radius: 24px 24px 8px 8px;
    }
    .glass-card {
      background: rgba(10, 31, 28, 0.03);
      backdrop-filter: blur(12px);
      border: 1px solid rgba(10, 31, 28, 0.08);
    }
  `]
})
export class CertificationProgramsComponent {
  readonly Medal = Medal;
  readonly Clock = Clock;
  readonly ChevronRight = ChevronRight;
  readonly ChevronLeft = ChevronLeft;
  readonly CheckCircle = CheckCircle;
  readonly Users = Users;
  readonly Star = Star;
  readonly BookOpen = BookOpen;
  readonly Target = Target;
  readonly Briefcase = Briefcase;
  readonly TrendingUp = TrendingUp;
  readonly Award = Award;
  readonly ShieldCheck = ShieldCheck;
  readonly FileText = FileText;
  readonly GraduationCap = GraduationCap;
  readonly ArrowRight = ArrowRight;
  readonly Play = Play;

  selectedCategory = 'all';

  categories = [
    { id: 'all', label: 'All Programs' },
    { id: 'Beginner', label: 'Foundations' },
    { id: 'Intermediate', label: 'Specialist' },
    { id: 'Advanced', label: 'Professional' }
  ];

  certifications = [
    {
      id: 'cert-1',
      title: 'Wildlife Awareness Professional',
      level: 'Advanced',
      icon: '🦌',
      completed: false,
      color: '#22c55e',
      description: 'Advanced training in wildlife behavior, safety protocols, and coexistence strategies. Learn species identification and emergency response.',
      metrics: { lessons: 18, assessments: 5, enrolled: '0.9K', rating: 4.9 },
      estimated: '4-5 weeks',
      skills: ['Wildlife Behavior', 'Safety Protocols', 'Species ID', 'Emergency Response'],
      requirements: [
        'Complete 18 video lessons',
        'Pass 5 knowledge assessments (85% minimum)',
        'Complete wildlife encounter simulation',
        'Submit field observation report'
      ]
    },
    {
      id: 'cert-2',
      title: 'Wilderness First Aid Certified',
      level: 'Advanced',
      icon: '🏥',
      completed: false,
      color: '#ef4444',
      description: 'Comprehensive wilderness first aid training covering emergency response, medical assessment, and treatment in remote environments.',
      metrics: { lessons: 24, assessments: 6, enrolled: '1.2K', rating: 5.0 },
      estimated: '5-6 weeks',
      skills: ['Emergency Response', 'Medical Assessment', 'Treatment Protocols', 'Evacuation Planning'],
      requirements: [
        'Complete 24 video lessons',
        'Pass 6 knowledge assessments (90% minimum)',
        'Complete practical scenarios',
        'Pass final comprehensive exam'
      ]
    },
    {
      id: 'cert-3',
      title: 'Certified Camping Essentials',
      level: 'Beginner',
      icon: '⛺',
      completed: false,
      color: '#3b82f6',
      description: 'Master the fundamentals of safe and comfortable camping. Learn site selection, tent setup, and Leave No Trace principles.',
      metrics: { lessons: 12, assessments: 3, enrolled: '2.5K', rating: 4.8 },
      estimated: '2-3 weeks',
      skills: ['Site Selection', 'Tent Setup', 'Camping Safety', 'Leave No Trace'],
      requirements: [
        'Complete 12 video lessons',
        'Pass 3 knowledge assessments (80% minimum)',
        'Submit campsite setup documentation'
      ]
    }
  ];

  benefits = [
    { title: 'Verified Credentials', description: 'Earn recognized certifications with verifiable digital badges', icon: Medal },
    { title: 'Skill Validation', description: 'Demonstrate your expertise to employers and organizations', icon: Target },
    { title: 'Career Growth', description: 'Advance your career in outdoor education and leadership', icon: TrendingUp }
  ];

  constructor(private router: Router) { }

  get filteredCertifications() {
    if (this.selectedCategory === 'all') return this.certifications;
    return this.certifications.filter(c => c.level === this.selectedCategory);
  }

  setCategory(categoryId: string) {
    this.selectedCategory = categoryId;
  }

  navigateBack() {
    this.router.navigate(['/academy']);
  }

  viewCertificate(certId: string) {
    this.router.navigate(['/academy/my-badges'], { queryParams: { cert: certId } });
  }
}
