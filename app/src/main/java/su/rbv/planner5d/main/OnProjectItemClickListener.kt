package su.rbv.planner5d.main

import su.rbv.planner5d.core_projects.domain.model.ProjectData

internal interface OnProjectItemClickListener {

    fun onProjectClick(projectData: ProjectData)
}