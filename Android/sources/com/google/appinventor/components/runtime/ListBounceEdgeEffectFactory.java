package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.widget.EdgeEffect;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListBounceEdgeEffectFactory extends RecyclerView.EdgeEffectFactory {
    private static final float FLING_TRANSLATION_MAGNITUDE = 0.5f;
    private static final float OVERSCROLL_TRANSLATION_MAGNITUDE = 0.2f;

    public EdgeEffect createEdgeEffect(RecyclerView recyclerView, int direction) {
        LinearLayoutManager layoutManager = recyclerView.getLayoutManager();
        return new BounceEdgeEffect(recyclerView, direction, layoutManager != null && layoutManager.getOrientation() == 0);
    }

    private static class BounceEdgeEffect extends EdgeEffect {
        private final int direction;
        private final boolean isHorizontal;
        private final RecyclerView recyclerView;
        private SpringAnimation translationAnim;

        public BounceEdgeEffect(RecyclerView recyclerView2, int direction2, boolean isHorizontal2) {
            super(recyclerView2.getContext());
            this.recyclerView = recyclerView2;
            this.direction = direction2;
            this.isHorizontal = isHorizontal2;
        }

        public void onPull(float deltaDistance) {
            super.onPull(deltaDistance);
            handlePull(deltaDistance);
        }

        public void onPull(float deltaDistance, float displacement) {
            super.onPull(deltaDistance, displacement);
            handlePull(deltaDistance);
        }

        private void handlePull(float deltaDistance) {
            int i = this.direction;
            translateRecyclerView(((float) (this.recyclerView.getWidth() * ((i == 3 || (this.isHorizontal && i == 2)) ? -1 : 1))) * deltaDistance * ListBounceEdgeEffectFactory.OVERSCROLL_TRANSLATION_MAGNITUDE);
            SpringAnimation springAnimation = this.translationAnim;
            if (springAnimation != null) {
                springAnimation.cancel();
            }
        }

        public void onRelease() {
            super.onRelease();
            if (getTranslation() != 0.0f) {
                SpringAnimation createAnim = createAnim();
                this.translationAnim = createAnim;
                if (createAnim != null) {
                    createAnim.start();
                }
            }
        }

        public void onAbsorb(int velocity) {
            super.onAbsorb(velocity);
            int i = this.direction;
            float translationVelocity = ((float) (((i == 3 || (this.isHorizontal && i == 2)) ? -1 : 1) * velocity)) * ListBounceEdgeEffectFactory.FLING_TRANSLATION_MAGNITUDE;
            SpringAnimation springAnimation = this.translationAnim;
            if (springAnimation != null) {
                springAnimation.cancel();
            }
            SpringAnimation createAnim = createAnim();
            this.translationAnim = createAnim;
            if (createAnim != null) {
                createAnim.setStartVelocity(translationVelocity);
                this.translationAnim.start();
            }
        }

        public boolean draw(Canvas canvas) {
            return false;
        }

        public boolean isFinished() {
            SpringAnimation springAnimation = this.translationAnim;
            return springAnimation == null || !springAnimation.isRunning();
        }

        private SpringAnimation createAnim() {
            return new SpringAnimation(this.recyclerView, this.isHorizontal ? SpringAnimation.TRANSLATION_X : SpringAnimation.TRANSLATION_Y).setSpring(new SpringForce().setFinalPosition(0.0f).setDampingRatio(ListBounceEdgeEffectFactory.FLING_TRANSLATION_MAGNITUDE).setStiffness(200.0f));
        }

        private float getTranslation() {
            return this.isHorizontal ? this.recyclerView.getTranslationX() : this.recyclerView.getTranslationY();
        }

        private void translateRecyclerView(float translationDelta) {
            if (this.isHorizontal) {
                this.recyclerView.setTranslationX(getTranslation() + translationDelta);
            } else {
                this.recyclerView.setTranslationY(getTranslation() + translationDelta);
            }
        }
    }
}
