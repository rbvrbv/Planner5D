package su.rbv.planner5d.ui_kit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import su.rbv.planner5d.R;
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData;

public class ProjectPreviewView extends View {

    private static final float WALLS_THICKNESS = 20f;
    private static final float ROOM_BORDERS_THICKNESS = 3f;

    private ProjectPreviewData data = null;
    private String floorNameToDraw = null;

    private float dataOffsetX = 0f;
    private float dataOffsetY = 0f;

    private float ratio = 1f;

    private float offsetX = 0f;
    private float offsetY = 0f;

    private final List<Path> roomPaths = new ArrayList<>();
    private final Path pathWall = new Path();

    private final Paint paintRooms = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint paintRoomBorders = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint paintWalls = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ProjectPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintRooms.setStyle(Paint.Style.FILL);

        paintRoomBorders.setStyle(Paint.Style.STROKE);
        paintRoomBorders.setStrokeWidth(ROOM_BORDERS_THICKNESS);

        paintWalls.setStrokeCap(Paint.Cap.SQUARE);
        paintWalls.setStyle(Paint.Style.STROKE);
        paintWalls.setStrokeWidth(WALLS_THICKNESS);

        loadCustomAttributes(attrs);
    }

    private void loadCustomAttributes(AttributeSet attrs) {
        TypedArray arrayAttr = getContext().obtainStyledAttributes(attrs, R.styleable.ProjectPreviewView);

        paintWalls.setColor(arrayAttr.getColor(R.styleable.ProjectPreviewView_wallsColor, Color.BLACK));
        paintRooms.setColor(arrayAttr.getColor(R.styleable.ProjectPreviewView_roomsFillColor, Color.LTGRAY));
        paintRoomBorders.setColor(arrayAttr.getColor(R.styleable.ProjectPreviewView_roomsBorderColor, Color.GRAY));
        arrayAttr.recycle();
    }

    /**
     * set project data preview to draw
     */
    public void setData(ProjectPreviewData data, String floorNameToDraw) {
        this.data = data;
        this.floorNameToDraw = floorNameToDraw;
        recalculate();
        invalidate();
    }

    /**
     *  select floor to draw
     */
    private void recalculate() {
        roomPaths.clear();
        pathWall.reset();
        if (data != null && data.getFloors() != null) {
            for (ProjectPreviewData.Floor floor : data.getFloors()) {
                if (floorNameToDraw != null && floorNameToDraw.equals(floor.getName())) {
                    List<ProjectPreviewData.Room> roomsToDraw = floor.getRooms();

                    if (roomsToDraw != null && calculateSizes(roomsToDraw)) {
                        for (ProjectPreviewData.Room roomToDraw : roomsToDraw) {
                            addRoom(roomToDraw);
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     *  calculate sizes of selected rooms
     *  @return true, if data calculated successfully
     */
    private boolean calculateSizes(List<ProjectPreviewData.Room> roomsToDraw) {
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float maxY = Float.MIN_VALUE;

        /* find min and max points coordinates */
        for (ProjectPreviewData.Room room : roomsToDraw) {
            List<ProjectPreviewData.Wall> wallsList = room.getWalls();

            if (wallsList != null) {
                for (ProjectPreviewData.Wall wall : room.getWalls()) {
                    List<ProjectPreviewData.Point> pointList = wall.getPoints();

                    if (pointList != null) {
                        for (ProjectPreviewData.Point point : wall.getPoints()) {
                            if (minX > point.getX()) minX = point.getX();
                            if (minY > point.getY()) minY = point.getY();
                            if (maxX < point.getX()) maxX = point.getX();
                            if (maxY < point.getY()) maxY = point.getY();
                        }
                    }
                }
            }
        }

        if (minX == Float.MAX_VALUE || (minY == Float.MAX_VALUE)) return false;

        /* calculate the removing offset in the data */
        dataOffsetX = -minX;
        dataOffsetY = -minY;

        /* calculate size of preview picture */
        float sizeX = maxX - minX;
        float sizeY = maxY - minY;

        /* calculate data to screen size convert ratio */
        float ratioX = sizeX / (getWidth() - getPaddingStart() - getPaddingEnd() - WALLS_THICKNESS);
        float ratioY = sizeY / (getHeight() - getPaddingTop() - getPaddingBottom() - WALLS_THICKNESS);

        /* choose ratio to fit the picture on the screen by horizontal/vertical borders */
        if (sizeX / ratioX <= getWidth() && sizeY / ratioX <= getHeight()) {
            ratio = ratioX;
            offsetX = 0f;
            offsetY = (getHeight() - sizeY / ratio) / 2;  /* offset to center vertical */
        } else {
            ratio = ratioY;
            offsetX = (getWidth() - sizeX / ratio) / 2;   /* offset to center horizontal */
            offsetY = 0f;
        }

        return true;
    }

    /**
     *  add room data to paths
     */
    private void addRoom(ProjectPreviewData.Room room) {
        Path pathRoom = new Path();

        List<ProjectPreviewData.Wall> walls = room.getWalls();

        if (walls == null) return;

        for (int idxWall = 0; idxWall < walls.size(); idxWall++) {
            ProjectPreviewData.Wall wall = walls.get(idxWall);

            if (wall != null) {
                List<ProjectPreviewData.Point> points = wall.getPoints();

                if (points != null) {
                    for (int idxPoint = 0; idxPoint < points.size(); idxPoint++) {
                        float x = calcX(points.get(idxPoint).getX());
                        float y = calcY(points.get(idxPoint).getY());
                        /* line is hidden, if it first path point or wall is hidden */
                        boolean isDrawHidden =
                            (idxWall == 0 && idxPoint == 0) ||
                                (wall.isHidden() != null && wall.isHidden() && idxPoint != 0);

                        if (idxWall == 0 || idxPoint > 0) {
                            if ((idxWall == 0 && idxPoint == 0) || isDrawHidden) {
                                pathWall.moveTo(x, y);
                            } else {
                                pathWall.lineTo(x, y);
                            }
                        }

                        if (pathRoom.isEmpty()) {
                            pathRoom.moveTo(x, y);
                        } else {
                            pathRoom.lineTo(x, y);
                        }
                    }
                }
            }
        }
        roomPaths.add(pathRoom);
    }

    /**
     *   calculate screen coordinates of points
     */
    private float calcX(float x) {
        return (x + dataOffsetX) / ratio + getPaddingStart() + offsetX + WALLS_THICKNESS / 2;
    }

    private float calcY(float y) {
        return (y + dataOffsetY) / ratio + getPaddingTop() + offsetY + WALLS_THICKNESS / 2;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (canvas != null) {
            for (Path path : roomPaths) {
                canvas.drawPath(path, paintRooms);
                canvas.drawPath(path, paintRoomBorders);
            }
            canvas.drawPath(pathWall, paintWalls);
        }
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        recalculate();
    }

}


