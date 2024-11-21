package frc.robot.Auto;

import java.util.ArrayList;

import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.path.PathPoint;
import com.pathplanner.lib.path.RotationTarget;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;

/**
 * Pathfinding
 */
public class Pathfinding {
    public enum POI {
        Note1(0.0,0.0,0.0),
        Note2(0.0,0.0,0.0);

        private double x_coordinates;
        private double y_coordinates;
        private Rotation2d angle;
        private Translation2d xy_coordinates;

        private POI(double x_coordinates, double y_coordinates, double angle) {
           this.x_coordinates = x_coordinates;
           this.y_coordinates = y_coordinates;
           this.angle = new Rotation2d(angle);
           this.xy_coordinates = new Translation2d(this.x_coordinates, this.y_coordinates);
        }
        /**
         * 
         * @return the angle Rotation2d object
         */
        private Rotation2d getAngle() {
            return this.angle;
        }
        private Translation2d getCoordinates() {
            return xy_coordinates;
        }
    }

    public Pathfinding() {

    }
    private PathPlannerPath makePath(ArrayList<POI> poi, Rotation2d angle) {
        ArrayList<PathPoint> point_list = new ArrayList<>();

        for (POI pathPoint_poi : poi) {
        PathPoint pathPoint = 
        new PathPoint(pathPoint_poi.getCoordinates(), new RotationTarget(0, angle));
        }

        PathPlannerPath.fromPathPoints(point_list, null, null);
        return null;
    }
}