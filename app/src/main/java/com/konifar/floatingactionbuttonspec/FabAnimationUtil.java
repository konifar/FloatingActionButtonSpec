package com.konifar.floatingactionbuttonspec;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

import io.codetail.animation.SupportAnimator;

public class FabAnimationUtil {

    private static final long DEFAULT_DURATION = 200L;
    private static final String ROTATION = "rotation";
    private static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();

    public static void rotateToSelect(final FloatingActionButton fab) {
        rotate(fab, 45f);
    }

    public static void rotateToUnSelect(final FloatingActionButton fab) {
        rotate(fab, 0f);
    }

    private static void rotate(final FloatingActionButton fab, float value) {
        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat(ROTATION, value);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fab, holder).setDuration(150L);
        animator.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
        animator.start();
    }

    public static void animateIn(final FloatingActionButton fab) {
        animateIn(fab, null);
    }

    public static void animateIn(final FloatingActionButton fab, final AnimateCallback callback) {
        fab.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.animate(fab)
                    .scaleX(1.0F)
                    .scaleY(1.0F)
                    .alpha(1.0F)
                    .setDuration(DEFAULT_DURATION)
                    .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                    .withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            if (callback != null) callback.onAnimationStart();
                        }

                        public void onAnimationCancel(View view) {
                        }

                        public void onAnimationEnd(View view) {
                            view.setVisibility(View.VISIBLE);
                            if (callback != null) callback.onAnimationEnd();
                        }
                    }).start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(fab.getContext(), R.anim.fab_in);
            anim.setDuration(200L);
            anim.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
            anim.setAnimationListener(new AnimationListenerAdapter() {
                public void onAnimationStart(Animation animation) {
                    if (callback != null) callback.onAnimationStart();
                }

                public void onAnimationEnd(Animation animation) {
                    fab.setVisibility(View.VISIBLE);
                    if (callback != null) callback.onAnimationEnd();
                }
            });
            fab.startAnimation(anim);
        }
    }

    public static void animateOut(final FloatingActionButton fab) {
        animateOut(fab, null);
    }

    public static void animateOut(final FloatingActionButton fab, final AnimateCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.animate(fab)
                    .scaleX(0.0F)
                    .scaleY(0.0F).alpha(0.0F)
                    .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                    .setDuration(DEFAULT_DURATION)
                    .withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            if (callback != null) callback.onAnimationStart();
                        }

                        public void onAnimationCancel(View view) {
                        }

                        public void onAnimationEnd(View view) {
                            view.setVisibility(View.INVISIBLE);
                            if (callback != null) callback.onAnimationEnd();
                        }
                    }).start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(fab.getContext(), R.anim.fab_out);
            anim.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
            anim.setDuration(200L);
            anim.setAnimationListener(new AnimationListenerAdapter() {
                public void onAnimationStart(Animation animation) {
                    if (callback != null) callback.onAnimationStart();
                }

                public void onAnimationEnd(Animation animation) {
                    fab.setVisibility(View.INVISIBLE);
                    if (callback != null) callback.onAnimationEnd();
                }
            });
            fab.startAnimation(anim);
        }
    }

    public static void moveIn(final FloatingActionButton fab, View view, ViewPropertyAnimatorListener listener) {
        int marginRight;
        int marginBottom;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            marginRight = 16;
            marginBottom = 16;
        } else {
            marginRight = 8;
            marginBottom = 0;
        }
        ViewCompat.animate(fab)
                .translationX(-(view.getWidth() / 2) + (fab.getWidth() / 2) + dpToPx(view.getContext(), marginRight))
                .translationY(-(view.getHeight() / 2) + (fab.getHeight() / 2) + dpToPx(view.getContext(), marginBottom))
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(150L)
                .withLayer()
                .setListener(listener)
                .start();
    }

    public static void moveOut(final FloatingActionButton fab, ViewPropertyAnimatorListener listener) {
        ViewCompat.animate(fab)
                .translationX(0)
                .translationY(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(150L)
                .withLayer()
                .setListener(listener)
                .start();
    }


    public static void transformIn(final FloatingActionButton fab, final View transformView) {
        moveIn(fab, transformView, new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
            }

            @Override
            public void onAnimationEnd(View view) {
                revealOn(fab, transformView, new RevealCallback() {
                    @Override
                    public void onRevealStart() {
                        fab.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onRevealEnd() {
                    }
                });
            }

            @Override
            public void onAnimationCancel(View view) {
            }
        });
    }

    public static void transformOut(final FloatingActionButton fab, final View transformView) {
        revealOff(fab, transformView, new RevealCallback() {
            @Override
            public void onRevealStart() {
            }

            @Override
            public void onRevealEnd() {
                moveOut(fab, new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        fab.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                    }
                });
            }
        });
    }

    public static void revealOn(final FloatingActionButton fab, View transformView, final RevealCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(
                    transformView,
                    transformView.getWidth() / 2,
                    transformView.getHeight() / 2,
                    fab.getWidth(),
                    (float) Math.hypot(transformView.getWidth(), transformView.getHeight()));
            transformView.setVisibility(View.VISIBLE);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    callback.onRevealStart();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    callback.onRevealEnd();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        } else {
            SupportAnimator animator =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(
                            transformView,
                            transformView.getWidth() / 2,
                            transformView.getHeight() / 2,
                            fab.getWidth(),
                            (float) Math.hypot(transformView.getWidth(), transformView.getHeight()));
            transformView.setVisibility(View.VISIBLE);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addListener(new io.codetail.animation.SupportAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart() {
                    callback.onRevealStart();
                }

                @Override
                public void onAnimationEnd() {
                    callback.onRevealEnd();
                }

                @Override
                public void onAnimationCancel() {
                }

                @Override
                public void onAnimationRepeat() {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        }
    }

    public static void revealOff(final FloatingActionButton fab, final View transformView, final RevealCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SupportAnimator animator =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(
                            transformView,
                            transformView.getWidth() / 2,
                            transformView.getHeight() / 2,
                            (float) Math.hypot(transformView.getWidth(), transformView.getHeight()),
                            fab.getWidth());
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addListener(new io.codetail.animation.SupportAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart() {
                    callback.onRevealStart();
                }

                @Override
                public void onAnimationEnd() {
                    transformView.setVisibility(View.INVISIBLE);
                    callback.onRevealEnd();
                }

                @Override
                public void onAnimationCancel() {
                }

                @Override
                public void onAnimationRepeat() {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        } else {
            SupportAnimator animator =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(
                            transformView,
                            transformView.getWidth() / 2,
                            transformView.getHeight() / 2,
                            (float) Math.hypot(transformView.getWidth(), transformView.getHeight()),
                            fab.getWidth());
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addListener(new io.codetail.animation.SupportAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart() {
                    callback.onRevealStart();
                }

                @Override
                public void onAnimationEnd() {
                    transformView.setVisibility(View.INVISIBLE);
                    callback.onRevealEnd();
                }

                @Override
                public void onAnimationCancel() {
                }

                @Override
                public void onAnimationRepeat() {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        }
    }

    public static float dpToPx(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }

    public interface AnimateCallback {
        public void onAnimationStart();

        public void onAnimationEnd();
    }

    public interface RevealCallback {
        public void onRevealStart();

        public void onRevealEnd();
    }

    static class AnimationListenerAdapter implements Animation.AnimationListener {
        AnimationListenerAdapter() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }
}
