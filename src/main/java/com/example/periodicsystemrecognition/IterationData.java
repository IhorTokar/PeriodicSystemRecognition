package com.example.periodicsystemrecognition;

public class IterationData {
    private final int iteration;
    private final double nextX;
    private final double nextV;
    private final String jacobian;

    public IterationData(int iteration, double nextX, double nextV, String jacobian) {
        this.iteration = iteration;
        this.nextX = nextX;
        this.nextV = nextV;
        this.jacobian = jacobian;
    }

    public int getIteration() {
        return iteration;
    }

    public double getNextX() {
        return nextX;
    }

    public double getNextV() {
        return nextV;
    }

    public String getJacobian() {
        return jacobian;
    }
}
